package on2014_09.on2014_09_28_Codeforces_Round__270.D___Design_Tutorial__Inverse_the_Problem;



import net.egork.collections.intervaltree.LCA;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] distance = IOUtils.readIntTable(in, count, count);
		for (int i = 0; i < count; i++) {
			if (distance[i][i] != 0) {
				out.printLine("NO");
				return;
			}
			for (int j = 0; j < count; j++) {
				if (distance[i][j] != distance[j][i] || distance[i][j] == 0 && i != j) {
					out.printLine("NO");
					return;
				}
			}
		}
		int total = count * (count - 1) / 2;
		Graph graph = new BidirectionalGraph(count, count - 1);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		int[] from = new int[total];
		int[] to = new int[total];
		int[] weight = new int[total];
		int id = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				from[id] = i;
				to[id] = j;
				weight[id++] = distance[i][j];
			}
		}
		int[] order = ArrayUtils.order(weight);
		for (int i : order) {
			if (setSystem.join(from[i], to[i])) {
				graph.addWeightedEdge(from[i], to[i], weight[i]);
			}
		}
		long[] fromRoot = new long[count];
		dfs(0, -1, 0L, graph, fromRoot);
		LCA lca = new LCA(graph);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				if (distance[i][j] != fromRoot[i] + fromRoot[j] - 2 * fromRoot[lca.getLCA(i, j)]) {
					out.printLine("NO");
					return;
				}
			}
		}
		out.printLine("YES");
	}

	private void dfs(int vertex, int last, long distance, Graph graph, long[] fromRoot) {
		fromRoot[vertex] = distance;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			if (graph.destination(i) != last) {
				dfs(graph.destination(i), vertex, distance + graph.weight(i), graph, fromRoot);
			}
		}
	}
}
