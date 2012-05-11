package net.egork.graph;

import net.egork.collections.Pair;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class GraphAlgorithms {
	public static<V> long dinic(Graph<V> graph, V source, V destination) {
		long totalFlow = 0;
		Map<V, Iterator<Edge<V>>> nextEdge = new HashMap<V, Iterator<Edge<V>>>();
		while (true) {
			Map<V, Integer> distance = edgeDistances(graph, source);
			if (!distance.containsKey(destination))
				break;
			nextEdge.clear();
			totalFlow += dinicImpl(graph, source, destination, Long.MAX_VALUE, distance, nextEdge);
		}
		return totalFlow;
	}

	private static<V> Map<V, Integer> edgeDistances(Graph<V> graph, V source) {
		Queue<V> queue = new ArrayBlockingQueue<V>(graph.vertices().size());
		Map<V, Integer> distance = new HashMap<V, Integer>();
		distance.put(source, 0);
		queue.add(source);
		while (!queue.isEmpty()) {
			V current = queue.poll();
			for (Edge<V> edge : graph.getIncident(current)) {
				if (edge.getCapacity() == 0)
					continue;
				V next = edge.getDestination();
				if (!distance.containsKey(next)) {
					distance.put(next, distance.get(current) + 1);
					queue.add(next);
				}
			}
		}
		return distance;
	}

	private static<V> long dinicImpl(Graph<V> graph, V source, V destination, long flow, Map<V, Integer> distance, Map<V, Iterator<Edge<V>>> nextEdge) {
		if (source.equals(destination))
			return flow;
		if (flow == 0 || distance.get(source).equals(distance.get(destination)))
			return 0;
		Iterator<Edge<V>> incident = nextEdge.get(source);
		if (incident == null)
			nextEdge.put(source, incident = graph.getIncident(source).iterator());
		int totalPushed = 0;
		while (incident.hasNext()) {
			Edge<V> edge = incident.next();
			V nextDestination = edge.getDestination();
			if (edge.getCapacity() == 0 || !distance.get(nextDestination).equals(distance.get(source) + 1))
				continue;
			long pushed = dinicImpl(graph, nextDestination, destination, Math.min(flow, edge.getCapacity()),
				distance, nextEdge);
			if (pushed != 0) {
				edge.pushFlow(pushed);
				flow -= pushed;
				totalPushed += pushed;
				if (flow == 0)
					return totalPushed;
			}
		}
		return totalPushed;
	}

	public static<V> Pair<Long, Long> minCostMaxFlow(Graph<V> graph, V source, V destination) {
		return minCostMaxFlow(graph, source, destination, Long.MAX_VALUE);
	}

	public static<V> Pair<Long, Long> minCostMaxFlow(Graph<V> graph, V source, V destination, long maxFlow) {
		long cost = 0;
		long flow = 0;
		Map<V, Long> phi = fordBellman(graph, source, true).first;
		while (maxFlow != 0) {
			Pair<Map<V, Long>, Map<V, Edge<V>>> result = dijkstraAlgorithm(graph, source, phi);
			if (!result.first.containsKey(destination))
				return Pair.makePair(cost, flow);
			for (Map.Entry<V, Long> entry : result.first.entrySet())
				phi.put(entry.getKey(), phi.get(entry.getKey()) + entry.getValue());
			V vertex = destination;
			long currentFlow = maxFlow;
			long currentCost = 0;
			while (vertex != source) {
				Edge<V> edge = result.second.get(vertex);
				currentFlow = Math.min(currentFlow, edge.getCapacity());
				currentCost += edge.getWeight();
				vertex = edge.getSource();
			}
			maxFlow -= currentFlow;
			cost += currentCost * currentFlow;
			flow += currentFlow;
			vertex = destination;
			while (vertex != source) {
				Edge<V> edge = result.second.get(vertex);
				edge.pushFlow(currentFlow);
				vertex = edge.getSource();
			}
		}
		return Pair.makePair(cost, flow);
	}

	public static<V> Pair<Map<V, Long>, Map<V, Edge<V>>> fordBellman(Graph<V> graph, V source) {
		return fordBellman(graph, source, false);
	}

	public static<V> Pair<Map<V, Long>, Map<V, Edge<V>>> fordBellman(Graph<V> graph, V source, boolean ignoreEmptyEdges) {
		Map<V, Long> distances = new HashMap<V, Long>();
		distances.put(source, 0L);
		Map<V, Edge<V>> last = new HashMap<V, Edge<V>>();
		final Set<V> inQueue = new HashSet<V>();
		Queue<V> queue = new ArrayBlockingQueue<V>(graph.vertices().size()) {
			@Override
			public boolean add(V v) {
				inQueue.add(v);
				return super.add(v);
			}

			@Override
			public V poll() {
				V result = super.poll();
				inQueue.remove(result);
				return result;
			}
		};
		queue.add(source);
		int stepCount = 0;
		int maxSteps = graph.vertices().size();
		maxSteps *= 2 * maxSteps;
		while (!queue.isEmpty()) {
			V vertex = queue.poll();
			for (Edge<V> edge : graph.getIncident(vertex)) {
				long total = distances.get(vertex) + edge.getWeight();
				V destination = edge.getDestination();
				if ((!ignoreEmptyEdges || edge.getCapacity() != 0) && (!distances.containsKey(destination) || distances.get(destination) > total)) {
					distances.put(destination, total);
					last.put(destination, edge);
					if (!inQueue.contains(destination))
						queue.add(destination);
				}
			}
			if (++stepCount > maxSteps)
				return null;
		}
		return Pair.makePair(distances, last);
	}

	public static<V> Pair<Map<V, Long>, Map<V, Edge<V>>> dijkstraAlgorithm(Graph<V> graph, V source) {
		Map<V, Long> distance = new HashMap<V, Long>();
		Queue<Pair<Long, V>> queue = new PriorityQueue<Pair<Long, V>>();
		Map<V, Edge<V>> last = new HashMap<V, Edge<V>>();
		distance.put(source, 0L);
		queue.add(Pair.makePair(0L, source));
		Set<V> processed = new HashSet<V>();
		while (!queue.isEmpty()) {
			V current = queue.poll().second;
			if (processed.contains(current))
				continue;
			processed.add(current);
			for (Edge<V> edge : graph.getIncident(current)) {
				V next = edge.getDestination();
				long total = edge.getWeight() + distance.get(current);
				if (!distance.containsKey(next) || distance.get(next) > total) {
					distance.put(next, total);
					last.put(next, edge);
					queue.add(Pair.makePair(total, next));
				}
			}
		}
		return Pair.makePair(distance, last);
	}

	public static<V> Pair<Map<V, Long>, Map<V, Edge<V>>> dijkstraAlgorithm(Graph<V> graph, V source, Map<V, Long> phi) {
		Map<V, Long> distance = new HashMap<V, Long>();
		Queue<Pair<Long, V>> queue = new PriorityQueue<Pair<Long, V>>();
		Map<V, Edge<V>> last = new HashMap<V, Edge<V>>();
		distance.put(source, 0L);
		queue.add(Pair.makePair(0L, source));
		Set<V> processed = new HashSet<V>();
		while (!queue.isEmpty()) {
			V current = queue.poll().second;
			if (processed.contains(current))
				continue;
			processed.add(current);
			for (Edge<V> edge : graph.getIncident(current)) {
				if (edge.getCapacity() == 0)
					continue;
				V next = edge.getDestination();
				long total = edge.getWeight() - phi.get(next) + phi.get(current) + distance.get(current);
				if (!distance.containsKey(next) || distance.get(next) > total) {
					distance.put(next, total);
					last.put(next, edge);
					queue.add(Pair.makePair(total, next));
				}
			}
		}
		return Pair.makePair(distance, last);
	}

	public static<V> Map<V, Integer> colorGraphTwoColors(Graph<V> graph, boolean allowBadEdges) {
		final Map<V, Integer> coloring = new HashMap<V, Integer>();
		boolean correctColoring = new DFS<Boolean, Integer, V>(graph) {
			@Override
			protected Boolean enterUnvisited(V vertex, Integer parameters) {
				if (vertex == null)
					return true;
				coloring.put(vertex, parameters);
				return true;
			}

			@Override
			protected Boolean enterVisited(V vertex, Integer parameters) {
				return vertex == null || coloring.get(vertex).equals(parameters);
			}

			@Override
			protected Integer getParameters(V vertex, Boolean result, Integer parameters, Edge<V> edge,
				AtomicBoolean enterVertex)
			{
				if (vertex == null)
					return coloring.get(edge.getDestination());
				return 1 - parameters;
			}

			@Override
			protected Boolean processResult(V vertex, Boolean result, Integer parameters, Boolean callResult,
				AtomicBoolean continueProcess)
			{
				return result && callResult;
			}

			@Override
			protected Boolean exit(V vertex, Boolean result, Integer parameters) {
				return result;
			}
		}.run(null);
		if (!correctColoring && !allowBadEdges)
			return null;
		return coloring;
	}
}
