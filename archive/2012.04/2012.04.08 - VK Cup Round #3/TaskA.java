package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] type = IOUtils.readIntArray(in, count);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleOrientedGraph(count, from, to);
		boolean[] reachableFromStart = getReachable(graph, type, 1, -1);
		graph = GraphUtils.buildSimpleOrientedGraph(count, to, from);
		boolean[] reachableFromFinish = getReachable(graph, type, 2, 1);
		int[] answer = new int[count];
		for (int i = 0; i < count; i++) {
			if (reachableFromStart[i] && reachableFromFinish[i])
				answer[i] = 1;
		}
		out.printLine(Array.wrap(answer).toArray());
	}

	private boolean[] getReachable(int[][] graph, int[] type, int startValue, int forbiddenValue) {
		int[] queue = new int[graph.length];
		int size = 0;
		boolean[] result = new boolean[graph.length];
		for (int i = 0; i < type.length; i++) {
			if (type[i] == startValue) {
				queue[size++] = i;
				result[i] = true;
			}
		}
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			if (type[current] == forbiddenValue)
				continue;
			for (int j : graph[current]) {
				if (!result[j]) {
					result[j] = true;
					queue[size++] = j;
				}
			}
		}
		return result;
	}
}
