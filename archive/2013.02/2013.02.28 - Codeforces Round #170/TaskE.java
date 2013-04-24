package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		String[] asParent = new String[count];
		String[] asChild = new String[count];
		for (int i = 0; i < count; i++) {
			asParent[i] = "parent" + i;
			asChild[i] = "child" + i;
		}
		Graph<String> graph = new Graph<String>();
		for (int i = 0; i < count; i++) {
			graph.addFlowEdge("source", asParent[i], 2);
			graph.addFlowEdge(asChild[i], "sink", 1);
			for (int j = 0; j < count; j++) {
				if (y[i] > y[j])
					graph.addFlowWeightedEdge(asParent[i], asChild[j], Math.round(1e11 * Math.hypot(x[i] - x[j], y[i] - y[j])), 1);
			}
		}
		Pair<Long, Long> result = new MinCostFlow(graph, "source", "sink", false).minCostMaxFlow();
		if (result.second != count - 1) {
			out.printLine(-1);
			return;
		}
		out.printLine(result.first / 1e11);
	}
}
