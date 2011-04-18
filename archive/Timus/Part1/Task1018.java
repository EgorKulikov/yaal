package Timus.Part1;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFS;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.WeightedEdge;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task1018 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		final int n = in.readInt();
		int q = in.readInt();
		Graph graph = new BidirectionalGraph(n);
		for (int i = 0; i < n - 1; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int appleCount = in.readInt();
			graph.add(new WeightedEdge(from, to, appleCount));
		}
		out.println(new DFS<int[], Edge>(graph) {

			@Override
			protected int[] enterUnvisited(int vertex, Edge parameters) {
				int[] result = new int[n];
				if (parameters != null)
					Arrays.fill(result, (int) parameters.getWeight());
				return result;
			}

			@Override
			protected int[] enterVisited(int vertex, Edge parameters) {
				throw new RuntimeException();
			}

			@Override
			protected Edge getParameters(int vertex, int[] result, Edge parameters, Edge edge,
				AtomicBoolean enterVertex)
			{
				if (parameters != null && edge.getDestination() == parameters.getSource()) {
					enterVertex.set(false);
					return null;
				}
				return edge;
			}

			@Override
			protected int[] processResult(int vertex, int[] result, Edge parameters, int[] callResult,
				AtomicBoolean continueProcess)
			{
				for (int i = n - 1; i >= 0; i--) {
					for (int j = 1; j <= i; j++)
						result[i] = Math.max(result[i], result[j - 1] + callResult[i - j]);
				}
				return result;
			}

			@Override
			protected int[] exit(int vertex, int[] result, Edge parameters) {
				return result;
			}
		}.run(0, null)[q]);
	}
}

