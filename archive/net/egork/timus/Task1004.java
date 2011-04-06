package net.egork.timus;

import net.egork.arrays.ArrayUtils;
import net.egork.collections.Transformer;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.WeightedEdge;
import net.egork.io.IOUtils;
import net.egork.utils.exit.Exit;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.List;

public class Task1004 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		final int n = in.readInt();
		if (n == -1) {
			Exit.exit(in, out);
			return;
		}
		Graph graph = new BidirectionalGraph(n);
		long[][] distances = new long[n][n];
		ArrayUtils.fill(distances, Long.MAX_VALUE);
		int m = in.readInt();
		for (int i = 0; i < m; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int length = in.readInt();
			distances[from][to] = distances[to][from] = Math.min(distances[from][to], length);
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (distances[i][j] != Long.MAX_VALUE)
					graph.addEdge(new WeightedEdge(i, j, distances[i][j]));
			}
		}
		long result = Long.MAX_VALUE;
		int vertex = -1;
		for (int i = 0; i < n; i++) {
			GraphAlgorithms.MultiPathDistanceResult currentResult = GraphAlgorithms.leviteAlgorithm(graph, i, 2);
			long currentDistance = currentResult.getDistances()[i][1];
			if (currentDistance < result) {
				result = currentDistance;
				vertex = i;
			}
		}
		if (result == Long.MAX_VALUE)
			out.println("No solution.");
		else {
			GraphAlgorithms.MultiPathDistanceResult paths = GraphAlgorithms.leviteAlgorithm(graph, vertex, 2);
			List<Integer> path = GraphAlgorithms.getPath(paths, vertex, vertex, 1);
			path = path.subList(0, path.size() - 1);
			Transformer.transform(path, new Transformer<Integer>() {
				@Override
				public Integer transform(Integer value) {
					return value + 1;
				}
			});
			IOUtils.printCollection(path, out);
		}
	}
}

