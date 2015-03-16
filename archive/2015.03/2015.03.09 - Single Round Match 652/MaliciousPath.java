package net.egork;

import net.egork.collections.heap.Heap;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class MaliciousPath {
    public long minPath(int N, int K, int[] from, int[] to, int[] cost) {
		long[] min = new long[N];
		long[] max = new long[N];
		Graph graph = Graph.createWeightedGraph(N, from, to, ArrayUtils.asLong(cost));
		Heap heap = new Heap((a, b) -> Long.compare(max[a], max[b]), N);
		for (int i = 0; i <= K; i++) {
			if (i != 0) {
				for (int j = 0; j < N - 1; j++) {
					for (int k = graph.firstOutbound(j); k != -1; k = graph.nextOutbound(k)) {
						min[j] = Math.max(min[j], graph.weight(k) + max[graph.destination(k)]);
						min[j] = Math.min(min[j], Long.MAX_VALUE / 2);
					}
				}
			}
			Arrays.fill(max, Long.MAX_VALUE / 2);
			max[N - 1] = 0;
			for (int j = 0; j < N; j++) {
				heap.add(j);
			}
			for (int j = 0; j < N; j++) {
				int current = heap.poll();
				for (int k = graph.firstInbound(current); k != -1; k = graph.nextInbound(k)) {
					int next = graph.source(k);
					long weight = graph.weight(k) + max[current];
					if (max[next] > Math.max(min[next], weight)) {
						max[next] = Math.max(min[next], weight);
						heap.shiftUp(heap.getIndex(next));
					}
				}
			}
		}
		return max[0] == Long.MAX_VALUE / 2 ? -1 : max[0];
    }
}
