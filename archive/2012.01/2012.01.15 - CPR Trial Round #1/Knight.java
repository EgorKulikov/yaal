package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.WeightedFlowEdge;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Knight {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		Graph graph = new Graph(2 * vertexCount);
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int cost = in.readInt();
			graph.add(new WeightedFlowEdge(2 * from + 1, 2 * to, cost, 2));
			graph.add(new WeightedFlowEdge(2 * to + 1, 2 * from, cost, 2));
		}
		int countGuarded = in.readInt();
		boolean[] isGuarded = new boolean[vertexCount];
		for (int i = 0; i < countGuarded; i++)
			isGuarded[in.readInt() - 1] = true;
		for (int i = 0; i < vertexCount; i++)
			graph.add(new WeightedFlowEdge(2 * i, 2 * i + 1, 0, isGuarded[i] ? 1 : 2));
		Pair <Long, Long> result = GraphAlgorithms.minCostMaxFlow(graph, 1, 2 * vertexCount - 2, 2);
		if (result.second != 2)
			out.printLine(-1);
		else
			out.printLine(result.first);
	}
}
