package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		int[] depth = new int[count];
		int[] queue = new int[count];
		boolean[] processed = new boolean[count];
		processed[0] = true;
		depth[0] = 1;
		int size = 1;
		double answer = 0;
		for (int i = 0; i < size; i++) {
			answer += 1d / depth[queue[i]];
			for (int j : graph[queue[i]]) {
				if (!processed[j]) {
					processed[j] = true;
					queue[size++] = j;
					depth[j] = depth[queue[i]] + 1;
				}
			}
		}
		out.printLine(answer);
    }
}
