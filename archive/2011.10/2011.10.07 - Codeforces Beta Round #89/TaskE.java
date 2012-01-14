import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE implements Solver {
	private int timer = 0;

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
		boolean[] visited = new boolean[vertexCount];
		int[] fup = new int[vertexCount];
		int[] tin = new int[vertexCount];
		if (dfs(graph, visited, fup, tin, 0, -1)) {
			out.println(0);
			return;
		}
		boolean[] onPath = new boolean[vertexCount];
		Arrays.fill(visited, false);
		dfsPrint(graph, visited, 0, -1, out, onPath);
	}

	private void dfsPrint(int[][] graph, boolean[] visited, int vertex, int previous, PrintWriter out, boolean[] onPath) {
		if (previous != -1 && (!visited[vertex] || onPath[vertex]))
			out.println((previous + 1) + " " + (vertex + 1));
		if (visited[vertex])
			return;
		visited[vertex] = true;
		onPath[vertex] = true;
		for (int i : graph[vertex]) {
			if (i != previous)
				dfsPrint(graph, visited, i, vertex, out, onPath);
		}
		onPath[vertex] = false;
	}

	private boolean dfs(int[][] graph, boolean[] visited, int[] fup, int[] tin, int vertex, int previous) {
		visited[vertex] = true;
		tin[vertex] = fup[vertex] = timer++;
		for (int i : graph[vertex]) {
			if (i == previous)
				continue;
			if (visited[i])
				fup[vertex] = Math.min(fup[vertex], tin[i]);
			else {
				if (dfs(graph, visited, fup, tin, i, vertex))
					return true;
				fup[vertex] = Math.min(fup[vertex], fup[i]);
				if (fup[i] > tin[vertex])
					return true;
			}
		}
		return false;
	}
}

