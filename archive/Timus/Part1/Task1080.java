package Timus.Part1;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.SimpleEdge;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1080 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		Graph graph = new BidirectionalGraph(n);
		for (int i = 0; i < n; i++) {
			while (true) {
				int to = in.readInt();
				if (to == 0)
					break;
				graph.add(new SimpleEdge(i, to - 1));
			}
		}
		int[] coloring = GraphAlgorithms.colorGraphTwoColors(graph, false);
		if (coloring == null)
			out.println(-1);
		else {
			for (int color : coloring)
				out.print(color);
			out.println();
		}
	}
}

