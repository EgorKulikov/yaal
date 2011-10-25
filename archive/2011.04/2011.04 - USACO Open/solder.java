import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class solder implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		if (vertexCount == 1) {
			out.println(0);
			return;
		}
		if (vertexCount == 2) {
			out.println(1);
			return;
		}
		int[] from = new int[vertexCount - 1];
		int[] to = new int[vertexCount - 1];
		IOUtils.readIntArrays(in, from, to);
		int[] degree = new int[vertexCount];
		for (int i = 0; i < vertexCount - 1; i++) {
			degree[--from[i]]++;
			degree[--to[i]]++;
		}
		int[][] graph = new int[vertexCount][];
		for (int i = 0; i < vertexCount; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < vertexCount - 1; i++) {
			graph[from[i]][--degree[from[i]]] = to[i];
			graph[to[i]][--degree[to[i]]] = from[i];
		}
		if (vertexCount <= 2000) {
			long totalAnswer = Long.MAX_VALUE;
			for (int root = 0; root < vertexCount; root++) {
				if (graph[root].length != 1)
					continue;
				int[] order = rootGraph(root, graph);
				int[] minVertex = new int[vertexCount];
				int[] length = new int[vertexCount];
				boolean[] processed = new boolean[vertexCount];
				for (int i : order) {
					length[i] = Integer.MAX_VALUE;
					for (int j : graph[i]) {
						if (processed[j] && length[j] < length[i]) {
							length[i] = length[j] + 1;
							minVertex[i] = j;
						}
					}
					processed[i] = true;
					if (length[i] == Integer.MAX_VALUE)
						length[i] = 0;
				}
				long[] path = new long[vertexCount];
				Arrays.fill(processed, false);
				long answer = 0;
				for (int ii = vertexCount - 1; ii >= 0; ii--) {
					int i = order[ii];
					processed[i] = true;
					for (int j : graph[i]) {
						if (processed[j])
							continue;
						if (j == minVertex[i])
							path[j] = path[i] + 1;
						else
							path[j] = 1;
					}
					if (graph[i].length == 1 && i != root)
						answer += path[i] * path[i];
				}
				totalAnswer = Math.min(totalAnswer,  answer);
			}
			out.println(totalAnswer);
			return;
		}
		int root = -1;
		int minPathLength = Integer.MAX_VALUE;
		for (int i = 0; i < vertexCount; i++) {
			if (graph[i].length != 1) {
				int[] order = rootGraph(i, graph);
				int[] length = new int[vertexCount];
				int[] vertex = new int[vertexCount];
				boolean[] processed = new boolean[vertexCount];
				for (int j : order) {
					length[j] = Integer.MAX_VALUE;
					for (int k : graph[j]) {
						if (processed[k] && length[j] != Integer.MAX_VALUE) {
							int pathLength = length[j] + length[k] + 1;
							if (pathLength < minPathLength) {
								minPathLength = pathLength;
								root = vertex[j];
							}
						}
						if (processed[k] && length[k] < length[j]) {
							length[j] = length[k] + 1;
							vertex[j] = vertex[k];
						}
					}
					processed[j] = true;
					if (length[j] == Integer.MAX_VALUE) {
						length[j] = 0;
						vertex[j] = j;
					}
				}
				break;
			}
		}
		int[] order = rootGraph(root, graph);
		int[] minVertex = new int[vertexCount];
		int[] length = new int[vertexCount];
		boolean[] processed = new boolean[vertexCount];
		for (int i : order) {
			length[i] = Integer.MAX_VALUE;
			for (int j : graph[i]) {
				if (processed[j] && length[j] < length[i]) {
					length[i] = length[j] + 1;
					minVertex[i] = j;
				}
			}
			processed[i] = true;
			if (length[i] == Integer.MAX_VALUE)
				length[i] = 0;
		}
		long[] path = new long[vertexCount];
		Arrays.fill(processed, false);
		long answer = 0;
		for (int ii = vertexCount - 1; ii >= 0; ii--) {
			int i = order[ii];
			processed[i] = true;
			for (int j : graph[i]) {
				if (processed[j])
					continue;
				if (j == minVertex[i])
					path[j] = path[i] + 1;
				else
					path[j] = 1;
			}
			if (graph[i].length == 1 && i != root)
				answer += path[i] * path[i];
		}
		out.println(answer);
	}

	private int[] rootGraph(int root, int[][] graph) {
		int[] order = new  int[graph.length];
		order[graph.length - 1] = root;
		boolean[] visited = new boolean[graph.length];
		visited[root] = true;
		int pointer = graph.length - 2;
		for (int i = graph.length - 1; i >= 0; i--) {
			for (int child : graph[order[i]]) {
				if (!visited[child]) {
					visited[child] = true;
					order[pointer--] = child;
				}
			}
		}
		return order;
	}
}

