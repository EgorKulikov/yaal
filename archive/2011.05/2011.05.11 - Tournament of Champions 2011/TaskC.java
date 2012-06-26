import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskC implements Solver {
	private int timer;

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		for (int i = 0; i < edgeCount; i++) {
			from[i]--;
			to[i]--;
		}
		int[][] graph = GraphUtils.buildSimpleGraph(vertexCount, from, to);
		int result = edgeCount - dfs(graph, 0, -1, new int[vertexCount], new int[vertexCount], new boolean[vertexCount]);
		out.println(result);
	}

	private int dfs(int[][] graph, int vertex, int previous, int[] tin, int[] fup, boolean[] visited) {
		int result = 0;
		visited[vertex] = true;
		tin[vertex] = fup[vertex] = timer++;
		for (int to : graph[vertex]) {
			if (to == previous)
				continue;
			if (visited[to])
				fup[vertex] = Math.min(fup[vertex], tin[to]);
			else {
				result += dfs(graph, to, vertex, tin, fup, visited);
				fup[vertex] = Math.min(fup[vertex], fup[to]);
				if (fup[to] > tin[vertex])
					result++;
			}
		}
		return result;
	}
}

