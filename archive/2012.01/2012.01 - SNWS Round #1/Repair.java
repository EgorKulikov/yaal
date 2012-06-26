package net.egork;

import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Repair {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int source = in.readInt() - 1;
		int destination = in.readInt() - 1;
		Graph graph = new Graph(2 * vertexCount);
		for (int i = 0; i < vertexCount; i++)
			graph.add(new FlowEdge(2 * i, 2 * i + 1, 1));
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			graph.add(new FlowEdge(2 * from + 1, 2 * to, Integer.MAX_VALUE));
			graph.add(new FlowEdge(2 * to + 1, 2 * from, Integer.MAX_VALUE));
		}
		long answer = GraphAlgorithms.dinic(graph, 2 * source + 1, 2 * destination);
		if (answer >= Integer.MAX_VALUE)
			answer = 0;
		out.printLine(answer);
	}
}
