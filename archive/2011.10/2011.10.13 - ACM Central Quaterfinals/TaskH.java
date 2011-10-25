import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskH implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		for (int i = 0; i < count - 1; i++) {
			from[i]--;
			to[i]--;
		}
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		final int[][] weight = new int[count][count];
		for (int i = 0; i < count; i++)
			dfs(i, -1, weight[i], 0, graph);
		int[] degree = new int[count];
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++)
				degree[weight[i][j]]++;
		}
		int[][] edges = new int[count][];
		for (int i = 0; i < count; i++)
			edges[i] = new int[degree[i]];
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++)
				edges[weight[i][j]][--degree[weight[i][j]]] = (i << 11) + j;
		}
		int answer = 0;
		int mask = (1 << 11) - 1;
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = count - 1; i >= 0; i--) {
			for (int edge : edges[i]) {
				int first = edge >> 11;
				int second = edge & mask;
				if (setSystem.join(first, second))
					answer += i;
			}
		}
		out.println(answer);
	}

	private void dfs(int vertex, int previous, int[] weight, int curWeight, int[][] graph) {
		weight[vertex] = curWeight;
		for (int next : graph[vertex]) {
			if (next != previous)
				dfs(next, vertex, weight, curWeight + 1, graph);
		}
	}
}

