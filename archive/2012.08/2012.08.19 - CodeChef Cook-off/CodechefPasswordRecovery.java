package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CodechefPasswordRecovery {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int countMust = in.readInt();
		int[] degree = new int[72];
		for (int i = 0; i < 2 * countMust; i++)
			degree[decode(in.readCharacter())]++;
		boolean[][] graph = new boolean[72][72];
		int countCan = in.readInt();
		for (int i = 0; i < countCan; i++) {
			int from = decode(in.readCharacter());
			int to = decode(in.readCharacter());
			graph[from][to] = graph[to][from] = true;
		}
		boolean[] nonEven = new boolean[72];
		for (int i = 0; i < 72; i++)
			nonEven[i] = degree[i] % 2 == 1;
		boolean[] visited = new boolean[72];
		for (int i = 0; i < 72; i++) {
			if (!nonEven[i])
				continue;
			Arrays.fill(visited, false);
			nonEven[i] = false;
			int other = go(i, graph, visited, nonEven);
			if (other == -1) {
				out.printLine("NO");
				return;
			}
			nonEven[other] = false;
		}
		out.printLine("YES");
	}

	private int go(int vertex, boolean[][] graph, boolean[] visited, boolean[] nonEven) {
		if (nonEven[vertex])
			return vertex;
		if (visited[vertex])
			return -1;
		visited[vertex] = true;
		for (int i = 0; i < 72; i++) {
			if (graph[vertex][i]) {
				int result = go(i, graph, visited, nonEven);
				if (result != -1)
					return result;
			}
		}
		return -1;
	}

	private int decode(char c) {
		if (Character.isUpperCase(c))
			return c - 'A';
		else
			return c - 'a' + 26;
	}
}
