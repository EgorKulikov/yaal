package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class KingdomUnity {
	int timer;
	int[] tin, fup;
	boolean[] visited;
	int[][] graph;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int cost = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		graph = GraphUtils.buildSimpleGraph(count, from, to);
		timer = 0;
		tin = new int[count];
		fup = new int[count];
		visited = new boolean[count];
		int answer = dfs(0, -1);
		out.printLine(answer * cost);
    }

	private int dfs(int vertex, int last) {
		visited[vertex] = true;
		tin[vertex] = fup[vertex] = timer++;
		int answer = 0;
		boolean currentIsCut = false;
		int children = 0;
		for (int i : graph[vertex]) {
			if (i == last)
				continue;
			if (visited[i])
				fup[vertex] = Math.min(fup[vertex], tin[i]);
			else {
				answer += dfs(i, vertex);
				fup[vertex] = Math.min(fup[vertex], fup[i]);
				if (fup[i] >= tin[vertex] && last != -1)
					currentIsCut = true;
				children++;
			}
		}
		if (last == -1 && children > 1 || currentIsCut)
			answer++;
		return answer;
	}
}
