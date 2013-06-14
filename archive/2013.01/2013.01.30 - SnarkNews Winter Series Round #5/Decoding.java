package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Decoding {
	int[] remaining;
	int[][] next;
	boolean[] used;
	int[] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		remaining = new int[count];
		int edgeCount = in.readInt();
		int[] before = new int[edgeCount];
		int[] after = new int[edgeCount];
		IOUtils.readIntArrays(in, before, after);
		MiscUtils.decreaseByOne(before, after);
		next = GraphUtils.buildSimpleOrientedGraph(count, before, after);
		used = new boolean[count];
		for (int i : after)
			remaining[i]++;
		answer = new int[1 << count];
		Arrays.fill(answer, -1);
		out.printLine(go(0, 0));
	}

	private int go(int mask, int step) {
		if (answer[mask] != -1)
			return answer[mask];
		if (step == used.length)
			return answer[mask] = 1;
		answer[mask] = 0;
		for (int i = 0; i < used.length; i++) {
			if (remaining[i] == 0 && !used[i]) {
				used[i] = true;
				for (int j : next[i])
					remaining[j]--;
				answer[mask] += go(mask + (1 << i), step + 1);
				used[i] = false;
				for (int j : next[i])
					remaining[j]++;
			}
		}
		return answer[mask];
	}
}
