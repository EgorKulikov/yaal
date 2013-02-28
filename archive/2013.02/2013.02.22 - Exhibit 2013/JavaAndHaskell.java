package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JavaAndHaskell {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		Graph<String> graph = new Graph<String>();
		String[] left = new String[count];
		String[] right = new String[count];
		for (int i = 0; i < count; i++) {
			left[i] = "left" + i;
			right[i] = "right" + i;
			graph.addVertex(left[i]);
			graph.addVertex(right[i]);
		}
		for (int i = 1; i < count - 1; i++) {
			long bribe = in.readLong();
			graph.addFlowEdge(left[i], right[i], bribe);
		}
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			long cost = in.readLong();
			graph.addFlowEdge(right[from], left[to], cost);
			graph.addFlowEdge(right[to], left[from], cost);
		}
		out.printLine(MaxFlow.dinic(graph, right[0], left[count - 1]));
    }
}
