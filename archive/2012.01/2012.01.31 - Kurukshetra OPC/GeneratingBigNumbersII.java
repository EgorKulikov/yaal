package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GeneratingBigNumbersII {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] count = IOUtils.readIntArray(in, 8);
		Graph graph = new Graph(8 + 160 + 2);
		int source = graph.getSize() - 2;
		int sink = source + 1;
		for (int i = 0; i < 8; i++)
			graph.add(new FlowEdge(source, i, count[i]));
		long required = ArrayUtils.sumArray(count);
		for (int i = 0; ; i++) {
			graph.add(new FlowEdge(i + 8, sink, 1));
			for (int j = 0; j < 8; j++) {
				if ((i + 1) % (j + 2) != 0)
					graph.add(new FlowEdge(j, i + 8, 1));
			}
			required -= GraphAlgorithms.dinic(graph, source, sink);
			if (required == 0) {
				out.printLine(i + 1);
				return;
			}
		}
	}
}
