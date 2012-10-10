package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.*;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task9 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int base = in.readInt() - 1;
		Graph graph = new BidirectionalGraph(vertexCount);
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int time = in.readInt();
			graph.add(new WeightedEdge(from, to, time));
		}
		int horcuxCount = in.readInt();
		int[] vertex = new int[horcuxCount];
		int[] weight = new int[horcuxCount];
		IOUtils.readIntArrays(in, vertex, weight);
		MiscUtils.decreaseByOne(vertex);
		Pair<long[], Edge[]> result = GraphAlgorithms.dijkstraAlgorithm(graph, base);
		long answer = 0;
		for (int i = 0; i < horcuxCount; i++)
			answer += result.first[vertex[i]] * weight[i];
		out.printLine(answer);
	}
}
