package net.egork.graph.algorithms;

import net.egork.graph.Graph;
import net.egork.graph.edge.Edge;

import java.util.*;

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
}
