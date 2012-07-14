package net.egork.graph;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class DFS<R, P, V> {
	protected Graph<V> graph;
	private Map<V, Iterator<Edge<V>>> currentEdge;
	protected Set<V> visited;

	protected DFS(Graph<V> graph) {
		this.graph = graph;
		currentEdge = new HashMap<V, Iterator<Edge<V>>>();
		visited = new HashSet<V>();
	}

	protected abstract R enterUnvisited(V vertex, P parameters);
	protected abstract R enterVisited(V vertex, P parameters);
	protected abstract P getParameters(V vertex, R result, P parameters, Edge<V> edge, AtomicBoolean enterVertex);
	protected abstract R processResult(V vertex, R result, P parameters, R callResult, AtomicBoolean continueProcess);
	protected abstract R exit(V vertex, R result, P parameters);

	@SuppressWarnings({"unchecked"})
	public R run(V vertex, P parameters) {
		Stack<Action> actions = new Stack<Action>();
		actions.add(new Action(ActionType.ENTER, vertex, null, parameters));
		R result = null;
		while (!actions.isEmpty()) {
			Action action = actions.pop();
			vertex = action.vertex;
			switch (action.type) {
			case ENTER:
				if (visited.contains(vertex))
					result = enterVisited(vertex, action.parameters);
				else {
					result = enterUnvisited(action.vertex, action.parameters);
					visited.add(vertex);
					currentEdge.put(vertex, graph.getIncident(vertex).iterator());
					actions.push(new Action(ActionType.GET_PARAMETERS, vertex, result, action.parameters));
				}
				break;
			case GET_PARAMETERS:
				if (currentEdge.get(vertex).hasNext()) {
					Edge<V> edge = currentEdge.get(vertex).next();
					V destination = edge.getDestination();
					AtomicBoolean enter = new AtomicBoolean(true);
					parameters = getParameters(vertex, action.result, action.parameters, edge, enter);
					if (enter.get()) {
						actions.push(new Action(ActionType.PROCESS_RESULT, vertex, action.result, action.parameters));
						actions.push(new Action(ActionType.ENTER, destination, null, parameters));
					} else
						actions.push(new Action(ActionType.GET_PARAMETERS, vertex, action.result, action.parameters));
				} else
					actions.push(new Action(ActionType.EXIT, vertex, action.result, action.parameters));
				break;
			case PROCESS_RESULT:
				AtomicBoolean continueProcess = new AtomicBoolean(true);
				result = processResult(vertex, action.result, action.parameters, result, continueProcess);
				if (continueProcess.get())
					actions.push(new Action(ActionType.GET_PARAMETERS, vertex, result, action.parameters));
				else
					actions.push(new Action(ActionType.EXIT, vertex, result, action.parameters));
				break;
			case EXIT:
				result = exit(vertex, action.result, action.parameters);
				break;
			}
		}
		return result;
	}

	public R run(P parameters) {
		return run(parameters, graph.vertices());
	}

	public R run(P parameters, Collection<? extends V> order) {
		R result = enterUnvisited(null, parameters);
		for (V vertex : order) {
			AtomicBoolean enterVertex = new AtomicBoolean(true);
			P callParameters = getParameters(null, result, parameters, new SimpleEdge<V>(null, vertex), enterVertex);
			if (enterVertex.get()) {
				R callResult = run(vertex, callParameters);
				AtomicBoolean continueProcess = new AtomicBoolean(true);
				result = processResult(null, result, parameters, callResult, continueProcess);
				if (!continueProcess.get())
					break;
			}
		}
		return exit(null, result, parameters);
	}

	private static enum ActionType {
		ENTER,
		GET_PARAMETERS,
		PROCESS_RESULT,
		EXIT
	}

	private class Action {
		private final ActionType type;
		private final V vertex;
		private final R result;
		private final P parameters;

		private Action(ActionType type, V vertex, R result, P parameters) {
			this.type = type;
			this.vertex = vertex;
			this.result = result;
			this.parameters = parameters;
		}
	}
}
