package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.Pair;
import net.egork.graph.*;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class AnnualParade {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int queryCount = in.readInt();
		Graph<String> graph = new Graph<String>(4 * count, 4 * count * count);
		for (int i = 0; i < count; i++) {
			graph.addFlowEdge("source", "left" + i, 1);
			graph.addFlowEdge("right" + i, "sink", 1);
		}
		long[][] distance = new long[count][count];
		ArrayUtils.fill(distance, Long.MAX_VALUE / 2);
		for (int i = 0; i < count; i++)
			distance[i][i] = 0;
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int cost = in.readInt();
			distance[from][to] = Math.min(distance[from][to], cost);
		}
		String[] left = new String[count];
		String[] right = new String[count];
		for (int i = 0; i < count; i++) {
			left[i] = "left" + i;
			right[i] = "right" + i;
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (i != j && distance[i][j] != Long.MAX_VALUE / 2) {
					graph.addFlowWeightedEdge(left[i], right[j], distance[i][j], Long.MAX_VALUE);
					graph.addFlowWeightedEdge(left[i], left[j], distance[i][j], Long.MAX_VALUE);
				}
			}
		}
		long[] delta = new long[count + 1];
		Arrays.fill(delta, Long.MAX_VALUE / 2);
		delta[count] = 0;
		MinCostFlow<String> flow = new MinCostFlow<String>(graph, "source", "sink", false);
		for (int i = 1; i <= count; i++) {
			Pair<Long, Long> pair = flow.minCostMaxFlow(1);
			if (pair.second != 1 || pair.first >= 10000)
				break;
			delta[count - i] = delta[count - i + 1] + pair.first;
		}
		for (int i = 0; i < queryCount; i++) {
			long additionalCost = in.readInt();
			long best = Long.MAX_VALUE;
			for (int j = 0; j <= count; j++)
				best = Math.min(best, delta[j] + additionalCost * j);
			out.printLine(best);
		}
	}
}
