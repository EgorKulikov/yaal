package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	private int[][] banned;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		Graph<Integer> graph = new BidirectionalGraph<Integer>();
		for (int i = 0; i < count; i++)
			graph.addVertex(i + 1);
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt();
			int to = in.readInt();
			int time = in.readInt();
			graph.addWeightedEdge(from, to, time);
		}
		banned = new int[count][];
		for (int i = 0; i < count; i++) {
			int total = in.readInt();
			banned[i] = IOUtils.readIntArray(in, total);
		}
		out.printLine(dijkstraAlgorithm(graph, 1, count));
	}

	public long dijkstraAlgorithm(Graph<Integer> graph, Integer source, Integer destination) {
		int sourceID = graph.resolve(source);
		int destinationID = graph.resolve(destination);
		if (sourceID == destinationID)
			return 0;
		Pair<long[], int[]> result = dijkstraAlgorithmByID(graph, sourceID);
		if (result.second[destinationID] == -1)
			return -1;
		return result.first[destinationID];
	}

	public Pair<long[], int[]> dijkstraAlgorithmByID(Graph<Integer> graph, int sourceID) {
		int vertexCount = graph.getVertexCount();
		final long[] distance = new long[vertexCount];
		int[] last = new int[vertexCount];
		Arrays.fill(distance, Long.MAX_VALUE);
		Arrays.fill(last, -1);
		distance[sourceID] = 0;
		if (graph.isSparse()) {
			Heap heap = new Heap(vertexCount, new IntComparator() {
				public int compare(int first, int second) {
					return IntegerUtils.longCompare(distance[first], distance[second]);
				}
			}, vertexCount);
			heap.add(sourceID);
			while (!heap.isEmpty()) {
				int current = heap.poll();
				int edgeID = graph.firstOutbound[current];
				long canTravel = distance[current];
				while (canTravel <= Integer.MAX_VALUE && Arrays.binarySearch(banned[current], (int)canTravel) >= 0)
					canTravel++;
				while (edgeID != -1) {
					if (graph.removed[edgeID]) {
						edgeID = graph.nextOutbound[edgeID];
						continue;
					}
					int next = graph.to[edgeID];
					long total = graph.weight[edgeID] + canTravel;
					if (distance[next] > total) {
						distance[next] = total;
						if (heap.getIndex(next) == -1)
							heap.add(next);
						else
							heap.shiftUp(heap.getIndex(next));
						last[next] = edgeID;
					}
					edgeID = graph.nextOutbound[edgeID];
				}
			}
		} else {
			boolean[] visited = new boolean[vertexCount];
			for (int i = 0; i < vertexCount; i++) {
				int index = -1;
				long length = Long.MAX_VALUE;
				for (int j = 0; j < vertexCount; j++) {
					if (!visited[j] && distance[j] < length) {
						length = distance[j];
						index = j;
					}
				}
				if (index == -1)
					break;
				visited[index] = true;
				int edgeID = graph.firstOutbound[index];
				long canTravel = distance[index];
				while (canTravel <= Integer.MAX_VALUE && Arrays.binarySearch(banned[index], (int)canTravel) >= 0)
					canTravel++;
				while (edgeID != -1) {
					if (graph.removed[edgeID]) {
						edgeID = graph.nextOutbound[edgeID];
						continue;
					}
					int next = graph.to[edgeID];
					if (visited[next]) {
						edgeID = graph.nextOutbound[edgeID];
						continue;
					}
					long total = graph.weight[edgeID] + canTravel;
					if (distance[next] > total) {
						distance[next] = total;
						last[next] = edgeID;
					}
					edgeID = graph.nextOutbound[edgeID];
				}
			}
		}
		return Pair.makePair(distance, last);
	}
}

