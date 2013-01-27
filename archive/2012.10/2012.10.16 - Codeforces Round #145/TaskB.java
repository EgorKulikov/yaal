package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int red = in.readInt();
		int green = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		int[][] minBadness = new int[2][red + 1];
		int[][] next = new int[2][red + 1];
		ArrayUtils.fill(minBadness, Integer.MAX_VALUE);
		minBadness[0][0] = minBadness[1][0] = 0;
		int total = 0;
		int last = 0;
		for (int i : heights) {
			ArrayUtils.fill(next, Integer.MAX_VALUE);
			int cost = Math.min(i, last);
			for (int j = red; j >= 0; j--) {
				if (minBadness[0][j] != Integer.MAX_VALUE) {
					if (j <= red - i)
						next[0][j + i] = Math.min(next[0][j + i], minBadness[0][j]);
					next[1][j] = Math.min(next[1][j], minBadness[0][j] + cost);
				}
				if (minBadness[1][j] != Integer.MAX_VALUE) {
					next[1][j] = Math.min(next[1][j], minBadness[1][j]);
					if (j <= red - i)
						next[0][j + i] = Math.min(next[0][j + i], minBadness[1][j] + cost);
				}
			}
			last = i;
			total += i;
			int[][] temp = minBadness;
			minBadness = next;
			next = temp;
		}
		int answer = Integer.MAX_VALUE;
		for (int i = Math.max(0, total - green); i <= red; i++)
			answer = Math.min(answer, Math.min(minBadness[0][i], minBadness[1][i]));
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		out.printLine(answer);
	}
}
