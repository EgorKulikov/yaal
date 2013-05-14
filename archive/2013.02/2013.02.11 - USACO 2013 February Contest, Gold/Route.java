package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Route {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int leftCount = in.readInt();
		int rightCount = in.readInt();
		int edgeCount = in.readInt();
		int[] leftValue = IOUtils.readIntArray(in, leftCount);
		int[] rightValue = IOUtils.readIntArray(in, rightCount);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleOrientedGraph(leftCount, from, to);
		long[] result = new long[rightCount];
		for (int i = 0; i < rightCount; i++)
			result[i] = rightValue[i];
		long answer = 0;
		for (int i = 0; i < leftCount; i++) {
			long current = leftValue[i];
			Arrays.sort(graph[i]);
			for (int j : graph[i]) {
				long newCurrent = Math.max(current, result[j] + leftValue[i]);
				result[j] = Math.max(result[j], current + rightValue[j]);
				current = newCurrent;
			}
			answer = Math.max(answer, current);
		}
		for (long i : result)
			answer = Math.max(answer, i);
		out.printLine(answer);
	}
}
