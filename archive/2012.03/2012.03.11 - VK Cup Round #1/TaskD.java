package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	int[][] result;
	private int[][] graph;
	private int distance;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		distance = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		graph = GraphUtils.buildSimpleGraph(count, from, to);
		result = new int[count][distance + 1];
		out.printLine(go(0, -1));
	}

	private long go(int vertex, int last) {
		long answer = 0;
		for (int i : graph[vertex]) {
			if (i != last) {
				answer += go(i, vertex);
				for (int j = 1; j <= distance; j++)
					result[vertex][j] += result[i][j - 1];
			}
		}
		result[vertex][0]++;
		answer += result[vertex][distance];
		answer *= 2;
		for (int i : graph[vertex]) {
			if (i != last) {
				for (int j = 1; j <= distance - 1; j++)
					answer += (long)result[i][j - 1] * (result[vertex][distance - j] - result[i][distance - j - 1]);
			}
		}
		answer /= 2;
		return answer;
	}
}
