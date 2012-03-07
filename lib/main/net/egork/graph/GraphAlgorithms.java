package net.egork.graph;

import net.egork.collections.Pair;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class GraphAlgorithms {
	public static long dinic(Graph graph, int source, int destination) {
		long totalFlow = 0;
		int vertexCount = graph.getSize();
		int[] nextEdge = new int[vertexCount];
		while (true) {
			int[] distance = edgeDistances(graph, source).first;
			if (distance[destination] == Integer.MAX_VALUE)
				break;
			Arrays.fill(nextEdge, 0);
			totalFlow += dinicImpl(graph, source, destination, Long.MAX_VALUE, distance, nextEdge);
		}
		return totalFlow;
	}

	private static Pair<int[], Edge[]> edgeDistances(Graph graph, int source) {
		int size = graph.getSize();
		Queue<Integer> queue = new ArrayBlockingQueue<Integer>(size);
		boolean[] notReached = new boolean[size];
		Arrays.fill(notReached, true);
		int[] distance = new int[size];
		Edge[] last = new Edge[size];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[source] = 0;
		queue.add(source);
		notReached[source] = false;
		int iterationCount = 0;
		while (!queue.isEmpty()) {
			iterationCount++;
			if (iterationCount / size / size / size != 0)
				return null;
			int current = queue.poll();
			for (Edge edge : graph.getIncident(current)) {
				if (edge.getCapacity() == 0)
					continue;
				int next = edge.getDestination();
				if (distance[next] > distance[current] + 1) {
					distance[next] = distance[current] + 1;
					last[next] = edge;
					if (notReached[next]) {
						notReached[next] = false;
						queue.add(next);
					}
				}
			}
		}
		return Pair.makePair(distance, last);
	}

	private static long dinicImpl(Graph graph, int source, int destination, long flow, int[] distance, int[] nextEdge) {
		if (source == destination)
			return flow;
		if (flow == 0 || distance[source] == distance[destination])
			return 0;
		List<Edge> incident = graph.getIncident(source);
		int incidentSize = incident.size();
		int totalPushed = 0;
		for (int i = nextEdge[source]; i < incidentSize; i++) {
			Edge edge = incident.get(i);
			int nextDestination = edge.getDestination();
			if (distance[nextDestination] != distance[source] + 1)
				continue;
			long pushed = dinicImpl(graph, nextDestination, destination, Math.min(flow, edge.getCapacity()),
				distance, nextEdge);
			if (pushed != 0) {
				edge.pushFlow(pushed);
				flow -= pushed;
				totalPushed += pushed;
				if (flow == 0) {
					nextEdge[source] = i;
					return totalPushed;
				}
			}
		}
		nextEdge[source] = incidentSize;
		return totalPushed;
	}

	public static Pair<Long, Long> minCostMaxFlow(Graph graph, int source, int destination) {
		return minCostMaxFlow(graph, source, destination, Long.MAX_VALUE);
	}

	public static Pair<Long, Long> minCostMaxFlow(Graph graph, int source, int destination, long maxFlow) {
		long cost = 0;
		long flow = 0;
		long[] phi = new long[graph.getSize()];
		long[] initialDistances = fordBellman(graph, source, true).first;
		for (int i = 0; i < graph.getSize(); i++) {
			if (initialDistances[i] != Long.MAX_VALUE)
				phi[i] -= initialDistances[i];
		}
		while (maxFlow != 0) {
			Pair<long[], Edge[]> result = dijkstraAlgorithm(graph, source, phi);
			if (result.first[destination] == Long.MAX_VALUE)
				return Pair.makePair(cost, flow);
			for (int i = 0; i < graph.getSize(); i++) {
				if (result.first[i] != Long.MAX_VALUE)
					phi[i] -= result.first[i];
			}
			int vertex = destination;
			long currentFlow = maxFlow;
			long currentCost = 0;
			while (vertex != source) {
				currentFlow = Math.min(currentFlow, result.second[vertex].getCapacity());
				currentCost += result.second[vertex].getWeight();
				vertex = result.second[vertex].getSource();
			}
			maxFlow -= currentFlow;
			cost += currentCost * currentFlow;
			flow += currentFlow;
			vertex = destination;
			while (vertex != source) {
				result.second[vertex].pushFlow(currentFlow);
				vertex = result.second[vertex].getSource();
			}
		}
		return Pair.makePair(cost, flow);
	}

	public static Pair<long[], Edge[]> fordBellman(Graph graph, int source) {
		return fordBellman(graph, source, false);
	}

	public static Pair<long[], Edge[]> fordBellman(Graph graph, int source, boolean ignoreEmptyEdges) {
		long[] distances = new long[graph.getSize()];
		Arrays.fill(distances, Long.MAX_VALUE);
		distances[source] = 0;
		Edge[] last = new Edge[graph.getSize()];
		Set<Integer> viable = Collections.singleton(source);
		int stepCount = 0;
		while (!viable.isEmpty()) {
			Set<Integer> nextViable = new HashSet<Integer>();
			for (int i : viable) {
				for (Edge edge : graph.getIncident(i)) {
					long total = distances[i] + edge.getWeight();
					int destination = edge.getDestination();
					if (total < distances[destination] && (!ignoreEmptyEdges || edge.getCapacity() != 0)) {
						distances[destination] = total;
						last[destination] = edge;
						nextViable.add(destination);
					}
				}
			}
			viable = nextViable;
			stepCount++;
			if (stepCount > graph.getSize() + 1)
				return null;
		}
		return Pair.makePair(distances, last);
	}

	public static Pair<long[], Edge[]> dijkstraAlgorithm(Graph graph, int source) {
		int size = graph.getSize();
		final long[] distance = new long[size];
		Queue<Pair<Long, Integer>> queue = new PriorityQueue<Pair<Long, Integer>>(size);
		Edge[] last = new Edge[size];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[source] = 0;
		queue.add(Pair.makePair(0L, source));
		boolean[] processed = new boolean[size];
		while (!queue.isEmpty()) {
			int current = queue.poll().second;
			if (processed[current])
				continue;
			processed[current] = true;
			for (Edge edge : graph.getIncident(current)) {
				int next = edge.getDestination();
				long weight = edge.getWeight();
				if (distance[next] > distance[current] + weight) {
					distance[next] = distance[current] + weight;
					last[next] = edge;
					queue.add(Pair.makePair(distance[next], next));
				}
			}
		}
		return Pair.makePair(distance, last);
	}

	public static Pair<long[], Edge[]> dijkstraAlgorithm(Graph graph, int source, long[] phi) {
		int size = graph.getSize();
		final long[] distance = new long[size];
		Queue<Pair<Long, Integer>> queue = new PriorityQueue<Pair<Long, Integer>>(size);
		Edge[] last = new Edge[size];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[source] = 0;
		queue.add(Pair.makePair(0L, source));
		boolean[] processed = new boolean[size];
		while (!queue.isEmpty()) {
			int current = queue.poll().second;
			if (processed[current])
				continue;
			processed[current] = true;
			for (Edge edge : graph.getIncident(current)) {
				if (edge.getCapacity() == 0)
					continue;
				int next = edge.getDestination();
				long weight = edge.getWeight() + phi[next] - phi[current];
				if (distance[next] > distance[current] + weight) {
					distance[next] = distance[current] + weight;
					last[next] = edge;
					queue.add(Pair.makePair(distance[next], next));
				}
			}
		}
		return Pair.makePair(distance, last);
	}

	public static int[] colorGraphTwoColors(Graph graph, boolean allowBadEdges) {
		final int[] coloring = new int[graph.getSize()];
		boolean correctColoring = new DFS<Boolean, Integer>(graph) {
			@Override
			protected Boolean enterUnvisited(int vertex, Integer parameters) {
				if (vertex == -1)
					return true;
				coloring[vertex] = parameters;
				return true;
			}

			@Override
			protected Boolean enterVisited(int vertex, Integer parameters) {
				return vertex == -1 || coloring[vertex] == parameters;
			}

			@Override
			protected Integer getParameters(int vertex, Boolean result, Integer parameters, Edge edge,
				AtomicBoolean enterVertex)
			{
				if (vertex == -1)
					return coloring[edge.getDestination()];
				return 1 - parameters;
			}

			@Override
			protected Boolean processResult(int vertex, Boolean result, Integer parameters, Boolean callResult,
				AtomicBoolean continueProcess)
			{
				return result && callResult;
			}

			@Override
			protected Boolean exit(int vertex, Boolean result, Integer parameters) {
				return result;
			}
		}.run(null);
		if (!correctColoring && !allowBadEdges)
			return null;
		return coloring;
	}
}
