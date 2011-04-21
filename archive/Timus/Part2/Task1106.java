package Timus.Part2;

import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.SimpleEdge;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.List;

public class Task1106 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		Graph graph = new Graph(n);
		for (int i = 0; i < n; i++) {
			boolean hasEdge = false;
			while (true) {
				int vertex = in.readInt() - 1;
				if (vertex == -1)
					break;
				hasEdge = true;
				graph.add(new SimpleEdge(i, vertex));
			}
			if (!hasEdge) {
				out.println(0);
				return;
			}
		}
		final int[] coloring = GraphAlgorithms.colorGraphTwoColors(graph, true);
		List<Integer> result = CollectionUtils.filter(CollectionUtils.range(1, n), new Filter<Integer>() {
			public boolean accept(Integer value) {
				return coloring[value - 1] == 1;
			}
		});
		out.println(result.size());
		IOUtils.printCollection(result, out);
	}
}

