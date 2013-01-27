package net.egork.graph;

import net.egork.collections.Pair;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class MinCostFlow<V> {
	private final Graph<V> graph;
	private final int sourceID;
	private final int destinationID;
	private final long[] phi;
	private final long[] dijkstraResult;
	private final int[] lastEdge;
	private final Heap heap;
	private final int vertexCount;
	private final int[] visited;
	private int visitIndex;

	public MinCostFlow(Graph<V> graph, V source, V destination, boolean hasNegativeEdges) {
		this.graph = graph;
		this.sourceID = graph.resolve(source);
		this.destinationID = graph.resolve(destination);
		vertexCount = graph.getVertexCount();
		phi = new long[vertexCount];
		if (hasNegativeEdges)
			fordBellman();
		dijkstraResult = new long[vertexCount];
		lastEdge = new int[vertexCount];
		if (graph.isSparse()) {
			heap = new Heap(vertexCount, new IntComparator() {
				public int compare(int first, int second) {
					return IntegerUtils.longCompare(dijkstraResult[first], dijkstraResult[second]);
				}
			}, vertexCount);
			visited = null;
		} else {
			heap = null;
			visited = new int[vertexCount];
		}
	}

	private void fordBellman() {
		Arrays.fill(phi, Long.MAX_VALUE);
		phi[sourceID] = 0;
		boolean[] inQueue = new boolean[vertexCount];
		int[] queue = new int[vertexCount + 1];
		queue[0] = sourceID;
		inQueue[sourceID] = true;
		int stepCount = 0;
		int head = 0;
		int end = 1;
		int maxSteps = 2 * vertexCount * vertexCount;
		while (head != end) {
			int vertex = queue[head++];
			if (head == queue.length)
				head = 0;
			inQueue[vertex] = false;
			int edgeID = graph.firstOutbound[vertex];
			while (edgeID != -1) {
				long total = phi[vertex] + graph.weight[edgeID];
				int destination = graph.to[edgeID];
				if (!graph.removed[edgeID] && graph.capacity[edgeID] != 0 && phi[destination] > total) {
					phi[destination] = total;
					if (!inQueue[destination]) {
						queue[end++] = destination;
						inQueue[destination] = true;
						if (end == queue.length)
							end = 0;
					}
				}
				edgeID = graph.nextOutbound[edgeID];
			}
			if (++stepCount > maxSteps)
				throw new IllegalArgumentException("Graph contains negative cycle");
		}
	}

	public Pair<Long, Long> minCostMaxFlow() {
		return minCostMaxFlow(Long.MAX_VALUE);
	}

	public Pair<Long, Long> minCostMaxFlow(long maxFlow) {
		long cost = 0;
		long flow = 0;
		while (maxFlow != 0) {
			if (graph.isSparse())
				dijkstraAlgorithm();
			else
				dijkstraAlgorithmFull();
			if (lastEdge[destinationID] == -1)
				return Pair.makePair(cost, flow);
			for (int i = 0; i < dijkstraResult.length; i++) {
				if (dijkstraResult[i] != Long.MAX_VALUE)
					phi[i] += dijkstraResult[i];
			}
			int vertex = destinationID;
			long currentFlow = maxFlow;
			long currentCost = 0;
			while (vertex != sourceID) {
				int edgeID = lastEdge[vertex];
				currentFlow = Math.min(currentFlow, graph.capacity[edgeID]);
				currentCost += graph.weight[edgeID];
				vertex = graph.from[edgeID];
			}
			maxFlow -= currentFlow;
			cost += currentCost * currentFlow;
			flow += currentFlow;
			vertex = destinationID;
			while (vertex != sourceID) {
				int edgeID = lastEdge[vertex];
				graph.edges[edgeID].pushFlow(currentFlow);
				vertex = graph.from[edgeID];
			}
		}
		return Pair.makePair(cost, flow);
	}

	private void dijkstraAlgorithm() {
		Arrays.fill(dijkstraResult, Long.MAX_VALUE);
		Arrays.fill(lastEdge, -1);
		dijkstraResult[sourceID] = 0;
		heap.add(sourceID);
		while (!heap.isEmpty()) {
			int current = heap.poll();
			int edgeID = graph.firstOutbound[current];
			while (edgeID != -1) {
				if (graph.capacity[edgeID] == 0 || graph.removed[edgeID]) {
					edgeID = graph.nextOutbound[edgeID];
					continue;
				}
				int next = graph.to[edgeID];
				long total = graph.weight[edgeID] - phi[next] + phi[current] + dijkstraResult[current];
				if (dijkstraResult[next] > total) {
					dijkstraResult[next] = total;
					if (heap.getIndex(next) == -1)
						heap.add(next);
					else
						heap.shiftUp(heap.getIndex(next));
					lastEdge[next] = edgeID;
				}
				edgeID = graph.nextOutbound[edgeID];
			}
		}
	}

	private void dijkstraAlgorithmFull() {
		visitIndex++;
		Arrays.fill(dijkstraResult, Long.MAX_VALUE);
		lastEdge[destinationID] = -1;
		dijkstraResult[sourceID] = 0;
		for (int i = 0; i < vertexCount; i++) {
			int index = -1;
			long length = Long.MAX_VALUE;
			for (int j = 0; j < vertexCount; j++) {
				if (visited[j] != visitIndex && dijkstraResult[j] < length) {
					length = dijkstraResult[j];
					index = j;
				}
			}
			if (index == -1) {
				return;
			}
			visited[index] = visitIndex;
			int edgeID = graph.firstOutbound[index];
			while (edgeID != -1) {
				if (graph.capacity[edgeID] == 0 || graph.removed[edgeID]) {
					edgeID = graph.nextOutbound[edgeID];
					continue;
				}
				int next = graph.to[edgeID];
				if (visited[next] == visitIndex) {
					edgeID = graph.nextOutbound[edgeID];
					continue;
				}
				long total = graph.weight[edgeID] - phi[next] + phi[index] + length;
				if (dijkstraResult[next] > total) {
					dijkstraResult[next] = total;
					lastEdge[next] = edgeID;
				}
				edgeID = graph.nextOutbound[edgeID];
			}
		}
	}
}
