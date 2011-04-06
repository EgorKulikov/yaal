package net.egork.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class GraphAlgorithms {
	public static class DistanceResult {
		private final long[] distances;
		private final int[] last;

		public DistanceResult(long[] distances, int[] last) {
			this.distances = distances;
			this.last = last;
		}

		public long[] getDistances() {
			return distances;
		}

		public int[] getLast() {
			return last;
		}
	}

	public static DistanceResult leviteAlgorithm(Graph graph, int source) {
		int size = graph.getSize();
		Deque<Integer> queue = new ArrayDeque<Integer>(size);
		boolean[] processed = new boolean[size];
		boolean[] notReached = new boolean[size];
		Arrays.fill(notReached, true);
		long[] distance = new long[size];
		int[] last = new int[size];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[source] = 0;
		last[source] = -1;
		queue.add(source);
		notReached[source] = false;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			processed[current] = true;
			for (Edge edge : graph.getIncident(current)) {
				int next = edge.getDestination();
				long weight = edge.getWeight();
				if (distance[next] > distance[current] + weight) {
					distance[next] = distance[current] + weight;
					last[next] = current;
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
		int[] last = new int[size];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[source] = 0;
		last[source] = -1;
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
					last[next] = current;
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
