import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int pipeCount = in.readInt();
		int[] from = new int[pipeCount];
		int[] to = new int[pipeCount];
		int[] flow = new int[pipeCount];
		IOUtils.readIntArrays(in, from, to, flow);
		for (int i = 0; i < pipeCount; i++) {
			from[i]--;
			to[i]--;
		}
		int[][] graph = GraphUtils.buildOrientedGraph(count, from, to);
		int[][] transposed = GraphUtils.buildOrientedGraph(count, to, from);
		int pairCount = count - pipeCount;
		for (int i = 0; i < count; i++) {
			if (graph[i].length == 0 && transposed[i].length == 0)
				pairCount--;
		}
		out.println(pairCount);
		for (int i = 0; i < count; i++) {
			if (graph[i].length != 0 && transposed[i].length == 0) {
				int vertex = i;
				int maxFlow = Integer.MAX_VALUE;
				while (graph[vertex].length != 0) {
					maxFlow = Math.min(maxFlow, flow[graph[vertex][0]]);
					vertex = to[graph[vertex][0]];
				}
				out.println((i + 1) + " " + (vertex + 1) + " " + maxFlow);
			}
		}
	}
}

