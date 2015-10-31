package on2015_09.on2015_09_05_2015_TopCoder_Open_Algorithm.LuckyGrid;


import net.egork.collections.Pair;
import net.egork.collections.intcollection.Heap;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class LuckyGrid {
	public int findMinimumSum(String[] grid) {
		int n = grid.length;
		if (n != 11) {
			int sevens = -1;
			for (int j = 0; j <= n; j++) {
				int current = j * 4 + (n - j) * 7;
				boolean good = true;
				while (current != 0) {
					if (current % 10 != 4 && current % 10 != 7) {
						good = false;
					}
					current /= 10;
				}
				if (good) {
					sevens = n - j;
				}
			}
			if (sevens == -1) {
				return -1;
			}
			Graph graph = new Graph(2 * n + 2);
			int totalRequired = 0;
			int totalSevens = 0;
			for (int i = 0; i < n; i++) {
				int required = sevens;
				for (int j = 0; j < n; j++) {
					if (grid[i].charAt(j) == '7') {
						required--;
						totalSevens++;
					}
				}
				if (required < -1) {
					return -1;
				}
				totalRequired += Math.max(required, 0);
				graph.addFlowWeightedEdge(2 * n, i, -1000, Math.max(required, 0));
				if (required != -1) {
					graph.addFlowWeightedEdge(2 * n, i, 1, 1);
				}
				required = sevens;
				for (int j = 0; j < n; j++) {
					if (grid[j].charAt(i) == '7') {
						required--;
					}
				}
				if (required < -1) {
					return -1;
				}
				totalRequired += Math.max(required, 0);
				graph.addFlowWeightedEdge(n + i, 2 * n + 1, -1000, Math.max(required, 0));
				if (required != -1) {
					graph.addFlowWeightedEdge(n + i, 2 * n + 1, 1, 1);
				}
				for (int j = 0; j < n; j++) {
					if (grid[i].charAt(j) == '.') {
						graph.addFlowWeightedEdge(i, n + j, 0, 1);
					}
				}
			}
			Pair<Long, Long> pair = MinCostFlow.minCostMaxFlow(graph, 2 * n, 2 * n + 1, true);
			if (pair.first >= -(totalRequired - 1) * 1000L) {
				return -1;
			}
			return (int) (4 * n * n + (totalSevens + pair.second) * 3);
		}
		int answer = Integer.MAX_VALUE;
		boolean[][][] can = new boolean[2][2][1 << 11];
		boolean[][][] next = new boolean[2][2][1 << 11];
		for (int x = 0; x < (1 << 11); x++) {
			if (Integer.bitCount(x) > 1 && Integer.bitCount(x) < 10) {
				continue;
			}
			can[0][0][0] = true;
			can[1][0][0] = true;
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 11; j++) {
					ArrayUtils.fill(next, false);
					for (int k = 0; k < 2; k++) {
						for (int l = 0; l < 2; l++) {
							for (int m = 0; m < (1 << 11); m++) {
								if (!can[k][l][m]) {
									continue;
								}
								for (int b = 0; b < 2; b++) {
									if (grid[i].charAt(j) == '4' + (1 - b) * 3) {
										continue;
									}
									int nm = m;
									int nl = l;
									if ((x >> j & 1) != b) {
										if ((m >> j & 1) == 1) {
											continue;
										} else {
											nm += 1 << j;
										}
									}
									if (b != k) {
										if (l == 1) {
											continue;
										} else {
											nl = 1;
										}
									}
									next[k][nl][nm] = true;
								}
							}
						}
					}
					boolean[][][] temp = can;
					can = next;
					next = temp;
				}
				ArrayUtils.fill(next, false);
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < (1 << 11); m++) {
							if (can[k][l][m]) {
								next[0][0][m] = true;
								next[1][0][m] = true;
							}
						}
					}
				}
				boolean[][][] temp = can;
				can = next;
				next = temp;
			}
			for (int i = 0; i < (1 << 11); i++) {
				if (can[0][0][i]) {
					int current = 4 * 11 * 11;
					current += Integer.bitCount(x) * 3 * 11;
					current += (Integer.bitCount(i & (~x)) - Integer.bitCount(i & x)) * 3;
					answer = Math.min(answer, current);
				}
			}
		}
		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		return answer;
	}
}

class MinCostFlow {
	private final Graph graph;
	private final int source;
	private final int destination;
	private final long[] phi;
	private final long[] dijkstraResult;
	private final int[] lastEdge;
	private final Heap heap;
	private final int vertexCount;
	private final int[] visited;
	private int visitIndex;

	public MinCostFlow(Graph graph, int source, int destination, boolean hasNegativeEdges) {
		this.graph = graph;
		this.source = source;
		this.destination = destination;
		vertexCount = graph.vertexCount();
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
		phi[source] = 0;
		boolean[] inQueue = new boolean[vertexCount];
		int[] queue = new int[vertexCount + 1];
		queue[0] = source;
		inQueue[source] = true;
		int stepCount = 0;
		int head = 0;
		int end = 1;
		int maxSteps = 2 * vertexCount * vertexCount;
		while (head != end) {
			int vertex = queue[head++];
			if (head == queue.length)
				head = 0;
			inQueue[vertex] = false;
			int edgeID = graph.firstOutbound(vertex);
			while (edgeID != -1) {
				long total = phi[vertex] + graph.weight(edgeID);
				int destination = graph.destination(edgeID);
				if (graph.capacity(edgeID) != 0 && phi[destination] > total) {
					phi[destination] = total;
					if (!inQueue[destination]) {
						queue[end++] = destination;
						inQueue[destination] = true;
						if (end == queue.length)
							end = 0;
					}
				}
				edgeID = graph.nextOutbound(edgeID);
			}
			if (++stepCount > maxSteps)
				throw new IllegalArgumentException("Graph contains negative cycle");
		}
	}

	public static Pair<Long, Long> minCostMaxFlow(Graph graph, int source, int destination, boolean hasNegativeEdges) {
		return new MinCostFlow(graph, source, destination, hasNegativeEdges).minCostMaxFlow();
	}

	public static Pair<Long, Long> minCostMaxFlow(Graph graph, int source, int destination, boolean hasNegativeEdges,
		long maxFlow)
	{
		return new MinCostFlow(graph, source, destination, hasNegativeEdges).minCostMaxFlow(maxFlow);
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
			if (lastEdge[destination] == -1)
				return Pair.makePair(cost, flow);
			for (int i = 0; i < dijkstraResult.length; i++) {
				if (dijkstraResult[i] != Long.MAX_VALUE)
					phi[i] += dijkstraResult[i];
			}
			int vertex = destination;
			long currentFlow = maxFlow;
			long currentCost = 0;
			while (vertex != source) {
				int edgeID = lastEdge[vertex];
				currentFlow = Math.min(currentFlow, graph.capacity(edgeID));
				currentCost += graph.weight(edgeID);
				vertex = graph.source(edgeID);
			}
			maxFlow -= currentFlow;
			if (currentCost >= 0) {
				return Pair.makePair(cost, flow);
			}
			cost += currentCost * currentFlow;
			flow += currentFlow;
			vertex = destination;
			while (vertex != source) {
				int edgeID = lastEdge[vertex];
				graph.pushFlow(edgeID, currentFlow);
				vertex = graph.source(edgeID);
			}
		}
		return Pair.makePair(cost, flow);
	}

	private void dijkstraAlgorithm() {
		Arrays.fill(dijkstraResult, Long.MAX_VALUE);
		Arrays.fill(lastEdge, -1);
		dijkstraResult[source] = 0;
		heap.add(source);
		while (!heap.isEmpty()) {
			int current = heap.poll();
			int edgeID = graph.firstOutbound(current);
			while (edgeID != -1) {
				if (graph.capacity(edgeID) != 0) {
					int next = graph.destination(edgeID);
					long total = graph.weight(edgeID) - phi[next] + phi[current] + dijkstraResult[current];
					if (dijkstraResult[next] > total) {
						dijkstraResult[next] = total;
						if (heap.getIndex(next) == -1)
							heap.add(next);
						else
							heap.shiftUp(heap.getIndex(next));
						lastEdge[next] = edgeID;
					}
				}
				edgeID = graph.nextOutbound(edgeID);
			}
		}
	}

	private void dijkstraAlgorithmFull() {
		visitIndex++;
		Arrays.fill(dijkstraResult, Long.MAX_VALUE);
		lastEdge[destination] = -1;
		dijkstraResult[source] = 0;
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
			int edgeID = graph.firstOutbound(index);
			while (edgeID != -1) {
				if (graph.capacity(edgeID) != 0) {
					int next = graph.destination(edgeID);
					if (visited[next] != visitIndex) {
						long total = graph.weight(edgeID) - phi[next] + phi[index] + length;
						if (dijkstraResult[next] > total) {
								dijkstraResult[next] = total;
							lastEdge[next] = edgeID;
						}
					}
				}
				edgeID = graph.nextOutbound(edgeID);
			}
		}
	}
}