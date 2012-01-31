package net.egork.graph;

import net.egork.collections.ArrayUtils;
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
			long[] distance = edgeDistances(graph, source).distances;
			if (distance[destination] == Long.MAX_VALUE)
				break;
			Arrays.fill(nextEdge, 0);
			totalFlow += dinicImpl(graph, source, destination, Long.MAX_VALUE, distance, nextEdge);
		}
		return totalFlow;
	}

	private static DistanceResult edgeDistances(Graph graph, int source) {
		int size = graph.getSize();
		Queue<Integer> queue = new ArrayBlockingQueue<Integer>(size);
		boolean[] notReached = new boolean[size];
		Arrays.fill(notReached, true);
		long[] distance = new long[size];
		Edge[] last = new Edge[size];
		Arrays.fill(distance, Long.MAX_VALUE);
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
				long weight = 1;
				if (distance[next] > distance[current] + weight) {
					distance[next] = distance[current] + weight;
					last[next] = edge;
					if (notReached[next]) {
						notReached[next] = false;
						queue.add(next);
					}
				}
			}
		}
		return new DistanceResult(distance, last);
	}

	private static long dinicImpl(Graph graph, int source, int destination, long flow, long[] distance, int[] nextEdge) {
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

	public static class DistanceResult {
		public final long[] distances;
		public final Edge[] last;

		public DistanceResult(long[] distances, Edge[] last) {
			this.distances = distances;
			this.last = last;
		}
	}

	public static class MultiPathDistanceResult {
		private final long[][] distances;
		private final int[][] lastIndex;
		private final int[][] lastPathNumber;

		public MultiPathDistanceResult(long[][] distances, int[][] lastIndex, int[][] lastPathNumber) {
			this.distances = distances;
			this.lastIndex = lastIndex;
			this.lastPathNumber = lastPathNumber;
		}

		public long[][] getDistances() {
			return distances;
		}

		public int[][] getLastIndex() {
			return lastIndex;
		}

		public int[][] getLastPathNumber() {
			return lastPathNumber;
		}
	}

	public static Pair<Long, Long> minCostMaxFlow(Graph graph, int source, int destination) {
		return minCostMaxFlow(graph, source, destination, Long.MAX_VALUE);
	}

	public static Pair<Long, Long> minCostMaxFlow(Graph graph, int source, int destination, long maxFlow) {
		long cost = 0;
		long flow = 0;
		while (maxFlow != 0) {
			DistanceResult result = leviteAlgorithm(graph, source, true);
			if (result.distances[destination] == Long.MAX_VALUE)
				return Pair.makePair(cost, flow);
			int vertex = destination;
			long currentFlow = maxFlow;
			long currentCost = result.distances[destination];
			while (vertex != source) {
				currentFlow = Math.min(currentFlow, result.last[vertex].getCapacity());
				vertex = result.last[vertex].getSource();
			}
			maxFlow -= currentFlow;
			cost += currentCost * currentFlow;
			flow += currentFlow;
			vertex = destination;
			while (vertex != source) {
				result.last[vertex].pushFlow(currentFlow);
				vertex = result.last[vertex].getSource();
			}
		}
		return Pair.makePair(cost, flow);
	}

	@Deprecated
	public static DistanceResult leviteAlgorithm(Graph graph, int source) {
		return leviteAlgorithm(graph, source, false);
	}

	@Deprecated
	public static DistanceResult leviteAlgorithm(Graph graph, int source, boolean ignoreEmpty) {
		int size = graph.getSize();
		Deque<Integer> queue = new ArrayDeque<Integer>(size);
		boolean[] processed = new boolean[size];
		boolean[] notReached = new boolean[size];
		Arrays.fill(notReached, true);
		long[] distance = new long[size];
		Edge[] last = new Edge[size];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[source] = 0;
		queue.add(source);
		notReached[source] = false;
		int iterationCount = 0;
		while (!queue.isEmpty()) {
			iterationCount++;
			if (iterationCount / size / size / size != 0)
				return null;
			int current = queue.poll();
			processed[current] = true;
			for (Edge edge : graph.getIncident(current)) {
				if (ignoreEmpty && edge.getCapacity() == 0)
					continue;
				int next = edge.getDestination();
				long weight = edge.getWeight();
				if (distance[next] > distance[current] + weight) {
					distance[next] = distance[current] + weight;
					last[next] = edge;
					if (notReached[next]) {
						notReached[next] = false;
						queue.add(next);
					} else if (processed[next]) {
						processed[next] = false;
						queue.addFirst(next);
					}
				}
			}
		}
		return new DistanceResult(distance, last);
	}

	@Deprecated
	public static MultiPathDistanceResult leviteAlgorithm(Graph graph, int source, int numPath) {
		int size = graph.getSize();
		Deque<Pair<Integer, Integer>> queue = new ArrayDeque<Pair<Integer, Integer>>(size);
		boolean[][] processed = new boolean[size][numPath];
		boolean[][] notReached = new boolean[size][numPath];
		ArrayUtils.fill(notReached, true);
		long[][] distance = new long[size][numPath];
		int[][] lastIndex = new int[size][numPath];
		int[][] lastPathNumber = new int[size][numPath];
		ArrayUtils.fill(distance, Long.MAX_VALUE);
		ArrayUtils.fill(lastIndex, -1);
		ArrayUtils.fill(lastPathNumber, -1);
		distance[source][0] = 0;
		queue.add(Pair.makePair(source, 0));
		notReached[source][0] = false;
		int iterationCount = 0;
		while (!queue.isEmpty()) {
			iterationCount++;
			if (iterationCount / size / size / size != 0)
				return null;
			int current = queue.peek().first;
			int currentPath = queue.poll().second;
			processed[current][currentPath] = true;
			for (Edge edge : graph.getIncident(current)) {
				int next = edge.getDestination();
				long weight = edge.getWeight();
				if (lastIndex[current][currentPath] == next)
					continue;
				for (int i = 0; i < numPath; i++) {
					if (distance[next][i] > distance[current][currentPath] + weight) {
						for (int j = numPath - 1; j > i; j--) {
							distance[next][j] = distance[next][j - 1];
							lastIndex[next][j] = lastIndex[next][j - 1];
							lastPathNumber[next][j] = lastPathNumber[next][j - 1];
						}
						distance[next][i] = distance[current][currentPath] + weight;
						lastIndex[next][i] = current;
						lastPathNumber[next][i] = currentPath;
						if (notReached[next][i]) {
							notReached[next][i] = false;
							queue.add(Pair.makePair(next, i));
						} else if (processed[next][i]) {
							processed[next][i] = false;
							queue.addFirst(Pair.makePair(next, i));
						}
						break;
					}
				}
			}
		}
		return new MultiPathDistanceResult(distance, lastIndex, lastPathNumber);
	}

	public static DistanceResult dijkstraAlgorithm(Graph graph, int source) {
		int size = graph.getSize();
		final long[] distance = new long[size];
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(size, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (distance[o1] < distance[o2])
					return -1;
				if (distance[o1] > distance[o2])
					return 1;
				return 0;
			}
		});
		Edge[] last = new Edge[size];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[source] = 0;
		queue.add(source);
		boolean[] processed = new boolean[size];
		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (processed[current])
				continue;
			processed[current] = true;
			for (Edge edge : graph.getIncident(current)) {
				int next = edge.getDestination();
				long weight = edge.getWeight();
				if (distance[next] > distance[current] + weight) {
					distance[next] = distance[current] + weight;
					last[next] = edge;
					queue.add(next);
				}
			}
		}
		return new DistanceResult(distance, last);
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
