package net.egork.graph;

import net.egork.collections.ArrayUtils;

import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class DFS<R, P> {
	protected Graph graph;
	private Iterator<Edge>[] currentEdge;
	protected boolean[] visited;
	protected int vertexCount;

	protected DFS(Graph graph) {
		this.graph = graph;
		vertexCount = graph.getSize();
		//noinspection unchecked
		currentEdge = new Iterator[vertexCount];
		visited = new boolean[vertexCount];
	}

	protected abstract R enterUnvisited(int vertex, P parameters);
	protected abstract R enterVisited(int vertex, P parameters);
	protected abstract P getParameters(int vertex, R result, P parameters, Edge edge, AtomicBoolean enterVertex);
	protected abstract R processResult(int vertex, R result, P parameters, R callResult, AtomicBoolean continueProcess);
	protected abstract R exit(int vertex, R result, P parameters);

	@SuppressWarnings({"unchecked"})
	public R run(int vertex, P parameters) {
		Stack<Action> actions = new Stack<Action>();
		actions.add(new Action(ActionType.ENTER, vertex, null, parameters));
		R result = null;
		while (!actions.isEmpty()) {
			Action action = actions.pop();
			vertex = action.vertex;
			switch (action.type) {
			case ENTER:
				if (visited[vertex])
					result = enterVisited(vertex, action.parameters);
				else {
					result = enterUnvisited(action.vertex, action.parameters);
					visited[vertex] = true;
					currentEdge[vertex] = graph.getIncident(vertex).iterator();
					actions.push(new Action(ActionType.GET_PARAMETERS, vertex, result, action.parameters));
				}
				break;
			case GET_PARAMETERS:
				if (currentEdge[vertex].hasNext()) {
					Edge edge = currentEdge[vertex].next();
					int destination = edge.getDestination();
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
		return run(parameters, ArrayUtils.range(0, vertexCount - 1));
	}

	public R run(P parameters, int[] order) {
		R result = enterUnvisited(-1, parameters);
		for (int i : order) {
			AtomicBoolean enterVertex = new AtomicBoolean(true);
			P callParameters = getParameters(-1, result, parameters, new SimpleEdge(-1, i), enterVertex);
			if (enterVertex.get()) {
				R callResult = run(i, callParameters);
				AtomicBoolean continueProcess = new AtomicBoolean(true);
				result = processResult(-1, result, parameters, callResult, continueProcess);
				if (!continueProcess.get())
					break;
			}
		}
		return exit(-1, result, parameters);
	}

	private static enum ActionType {
		ENTER,
		GET_PARAMETERS,
		PROCESS_RESULT,
		EXIT
	}

	private class Action {
		private final ActionType type;
		private final int vertex;
		private final R result;
		private final P parameters;

		private Action(ActionType type, int vertex, R result, P parameters) {
			this.type = type;
			this.vertex = vertex;
			this.result = result;
			this.parameters = parameters;
		}
	}
}
