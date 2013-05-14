package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		Graph<String> graph = new Graph<String>(2 * count + 2, 2 * edgeCount + 2 * count + 4);
		String[][] vertices = new String[2][count];
		for (int i = 0; i < count; i++) {
			vertices[0][i] = "upper" + i;
			vertices[1][i] = "lower" + i;
			graph.addWeightedEdge(vertices[0][i], vertices[1][i], 1);
			graph.addWeightedEdge(vertices[1][i], vertices[0][i], 1);
		}
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			graph.addWeightedEdge(vertices[0][from], vertices[0][to], 0);
			graph.addWeightedEdge(vertices[1][to], vertices[1][from], 0);
		}
		int start = in.readInt() - 1;
		int end = in.readInt() - 1;
		graph.addWeightedEdge("source", vertices[0][start], 0);
		graph.addWeightedEdge("source", vertices[1][start], 0);
		graph.addWeightedEdge(vertices[0][end], "sink", 0);
		graph.addWeightedEdge(vertices[1][end], "sink", 0);
		out.printLine(ShortestDistance.dijkstraAlgorithm(graph, "source", "sink").first);
	}
}
