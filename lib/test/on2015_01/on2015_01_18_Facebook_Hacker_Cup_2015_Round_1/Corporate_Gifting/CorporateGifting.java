package on2015_01.on2015_01_18_Facebook_Hacker_Cup_2015_Round_1.Corporate_Gifting;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CorporateGifting {
	long[] answer;
	int[] at;
	long[] delta;
	int[][] graph;
	long[] totalDelta;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] parent = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(parent);
		answer = new long[count];
		at = new int[count];
		delta = new long[count];
		graph = new int[count][];
		IntList[] lists = new IntList[count];
		for (int i = 1; i < count; i++) {
			if (lists[parent[i]] == null) lists[parent[i]] = new IntArrayList();
			lists[parent[i]].add(i);
		}
		for (int i = 0; i < count; i++) {
			if (lists[i] == null) graph[i] = new int[0];
			else graph[i] = lists[i].toArray();
		}
		totalDelta = new long[count + 3];
		go(0);
		out.printLine("Case #" + testNumber + ":", answer[0]);
    }

	private void go(int vertex) {
		for (int i : graph[vertex]) go(i);
		Arrays.fill(totalDelta, 0, graph[vertex].length + 3, 0);
		for (int i : graph[vertex]) {
			answer[vertex] += answer[i];
			if (at[i] <= graph[vertex].length + 2) totalDelta[at[i]] += delta[i];
		}
		long min = Long.MAX_VALUE;
		long secondMin = Long.MAX_VALUE;
		for (int i = 1; i <= graph[vertex].length + 2; i++) {
			long current = i + totalDelta[i];
			if (min > current) {
				secondMin = min;
				min = current;
				at[vertex] = i;
			} else secondMin = Math.min(secondMin, current);
		}
		answer[vertex] += min;
		delta[vertex] = secondMin - min;
	}
}
