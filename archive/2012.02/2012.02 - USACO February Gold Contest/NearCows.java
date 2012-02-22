package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class NearCows {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxDistance = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[] cow = IOUtils.readIntArray(in, count);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		int[][] answer = new int[maxDistance + 1][count];
		for (int i = 0; i < count; i++) {
			for (int j : graph[i])
				answer[1][i] += cow[j];
		}
		for (int i = 2; i <= maxDistance; i++) {
			for (int j = 0; j < count; j++) {
				for (int k : graph[j])
					answer[i][j] += answer[i - 1][k] + cow[k];
				answer[i][j] -= graph[j].length * cow[j] + (graph[j].length - 1) * answer[i - 2][j];
			}
		}
		for (int i = 0; i < count; i++)
			out.printLine(answer[maxDistance][i] + cow[i]);
	}
}
