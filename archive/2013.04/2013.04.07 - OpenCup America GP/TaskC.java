package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		Graph<String> graph = new Graph<String>();
		String source = "source";
		String sink = "sink";
		String[] left = new String[count];
		String[] right = new String[count];
		for (int i = 0; i < count; i++) {
			left[i] = "left " + i;
			right[i] = "right" + i;
		}
		for (int i = 0; i < count; i++) {
			graph.addFlowEdge(source, left[i], 1);
			graph.addFlowEdge(right[i], sink, 1);
			for (int j = 0; j < count; j++) {
				if (i != j)
					graph.addFlowWeightedEdge(left[i], right[j], Math.round(Math.hypot(x[i] - x[j], y[i] - y[j]) * 1e9), 1);
			}
		}
		out.printLine(new MinCostFlow<String>(graph, source, sink, false).minCostMaxFlow().first / 2e9);
    }
}
