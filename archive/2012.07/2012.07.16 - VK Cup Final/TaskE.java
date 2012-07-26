package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	int[][] size;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		boolean[] possible = new boolean[count];
		size = new int[count][count];
		ArrayUtils.fill(size, -1);
		int[] possibleSize = new int[count];
		boolean[] curPossible = new boolean[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				possibleSize[j] = go(graph[i][j], i, graph);
			}
			Arrays.fill(curPossible, false);
			curPossible[0] = true;
			for (int j = 0; j < graph[i].length; j++) {
				for (int k = count - 1; k >= possibleSize[j]; k--) {
					if (curPossible[k - possibleSize[j]])
						curPossible[k] = true;
				}
			}
			for (int j = 0; j < count; j++)
				possible[j] |= curPossible[j];
		}
		int answer = 0;
		for (int i = 1; i < count - 1; i++) {
			if (possible[i] || possible[count - 1 - i])
				answer++;
		}
		out.printLine(answer);
		for (int i = 1; i < count - 1; i++) {
			if (possible[i] || possible[count - 1 - i])
				out.printLine(i, count - 1 - i);
		}
	}

	private int go(int vertex, int last, int[][] graph) {
		if (size[vertex][last] != -1)
			return size[vertex][last];
		size[vertex][last] = 1;
		for (int i : graph[vertex]) {
			if (i != last)
				size[vertex][last] += go(i, vertex, graph);
		}
		return size[vertex][last];
	}
}
