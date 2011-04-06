package net.egork.graph;

import net.egork.arrays.ArrayUtils;
import net.egork.collections.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class GraphAlgorithms {
	public static List<Integer> getPath(DistanceResult result, int source, int destination) {
		List<Integer> path = new ArrayList<Integer>();
		path.add(destination);
		while (destination != source) {
			destination = result.getLast()[destination];
			path.add(destination);
		}
		Collections.reverse(path);
		return path;
	}

	public static List<Integer> getPath(MultiPathDistanceResult result, int source, int destination, int pathNumber) {
		List<Integer> path = new ArrayList<Integer>();
		path.add(destination);
		while (destination != source || pathNumber != 0) {
			int nextDestination = result.getLastIndex()[destination][pathNumber];
			pathNumber = result.getLastPathNumber()[destination][pathNumber];
			destination = nextDestination;
			path.add(destination);
		}
		Collections.reverse(path);
		return path;
	}

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
		queue.add(new Pair<Integer, Integer>(source, 0));
		notReached[source][0] = false;
		while (!queue.isEmpty()) {
			int current = queue.peek().getFirst();
			int currentPath = queue.poll().getSecond();
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
							queue.add(new Pair<Integer, Integer>(next, i));
						} else if (processed[next][i]) {
							processed[next][i] = false;
							queue.addFirst(new Pair<Integer, Integer>(next, i));
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
