package on2015_12.on2015_12_18_Single_Round_Match_676.Farmville;



import net.egork.generated.collections.queue.IntArrayQueue;
import net.egork.generated.collections.queue.IntQueue;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class Farmville {
	public int minTime(String[] s, int[] time, int[] cost, int budget) {
		int n = s.length;
		int[] minStart = new int[n];
		int[] minEnd = new int[n];
		Graph graph = new Graph(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (s[i].charAt(j) == '1') {
					graph.addSimpleEdge(j, i);
				}
			}
		}
		int[] degree = new int[n];
		IntQueue queue = new IntArrayQueue();
		boolean[] reachable = new boolean[2 * n + 2];
		while (true) {
			Arrays.fill(degree, 0);
			Arrays.fill(minStart, 0);
			for (int i = 0; i < n; i++) {
				if (graph.firstInbound(i) == -1) {
					queue.add(i);
				}
				for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j))
					degree[graph.destination(j)]++;
			}
			while (!queue.isEmpty()) {
				int current = queue.poll();
				minEnd[current] = minStart[current] + time[current];
				for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
					int destination = graph.destination(j);
					degree[destination]--;
					minStart[destination] = Math.max(minStart[destination], minEnd[current]);
					if (degree[destination] == 0)
						queue.add(destination);
				}
			}
			int answer = ArrayUtils.maxElement(minEnd);
			Graph cGraph = new Graph(2 * n + 2);
			for (int i = 0; i < n; i++) {
				if (minStart[i] == 0)
					cGraph.addFlowEdge(2 * n, 2 * i, budget + 1);
				if (minEnd[i] == answer)
					cGraph.addFlowEdge(2 * i + 1, 2 * n + 1, budget + 1);
				if (time[i] != 0)
					cGraph.addFlowEdge(2 * i, 2 * i + 1, cost[i]);
				else
					cGraph.addFlowEdge(2 * i, 2 * i + 1, budget + 1);
				for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
					int destination = graph.destination(j);
					if (minEnd[i] == minStart[destination])
						cGraph.addFlowEdge(2 * i + 1, 2 * destination, budget + 1);
				}
			}
			long minPrice = MaxFlow.dinic(cGraph, 2 * n, 2 * n + 1);
			if (budget < minPrice) {
				return answer;
			}
			budget -= minPrice;
			queue.add(2 * n);
			Arrays.fill(reachable, false);
			reachable[2 * n] = true;
			while (!queue.isEmpty()) {
				int current = queue.poll();
				for (int i = cGraph.firstOutbound(current); i != -1; i = cGraph.nextOutbound(i)) {
					if (cGraph.capacity(i) != 0) {
						int destination = cGraph.destination(i);
						if (!reachable[destination]) {
							reachable[destination] = true;
							queue.add(destination);
						}
					}
				}
			}
			for (int i = 0; i < n; i++) {
				if (reachable[2 * i] && !reachable[2 * i + 1]) {
					time[i]--;
				}
			}
		}
	}
}
