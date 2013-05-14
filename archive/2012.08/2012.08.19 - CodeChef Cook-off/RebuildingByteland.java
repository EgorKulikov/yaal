package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.SimpleEdge;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RebuildingByteland {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		int warpCount = in.readInt();
		boolean[] mayBeWarped = new boolean[edgeCount];
		for (int i = 0; i < warpCount; i++)
			mayBeWarped[in.readInt() - 1] = true;
		Graph<Integer> graph = new BidirectionalGraph<Integer>();
		for (int i = 0; i < edgeCount; i++) {
			if (!mayBeWarped[i])
				graph.add(new SimpleEdge<Integer>(from[i], to[i]));
		}
		if (GraphAlgorithms.colorGraphTwoColors(graph, false) == null)
			out.printLine("NO");
		else
			out.printLine("YES");
	}
}
