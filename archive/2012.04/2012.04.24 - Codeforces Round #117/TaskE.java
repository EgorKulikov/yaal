package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int[] height = new int[count];
		int[] width = new int[count];
		IOUtils.readIntArrays(in, height, width);
		long[][] answer = new long[length + 1][2 * count];
		for (int i = 0; i < count; i++) {
			if (height[i] <= length)
				answer[height[i]][i]++;
			if (width[i] <= length && height[i] != width[i])
				answer[width[i]][i + count]++;
		}
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < 2 * count; j++) {
				if (answer[i][j] == 0)
					continue;
				answer[i][j] %= MOD;
				int requiredWidth = j < count ? width[j] : height[j - count];
				if (i + requiredWidth > length)
					continue;
				for (int k = 0; k < count; k++) {
					if (k == j || k + count == j)
						continue;
					if (height[k] == requiredWidth)
						answer[i + requiredWidth][k] += answer[i][j];
					else if (width[k] == requiredWidth)
						answer[i + requiredWidth][k + count] += answer[i][j];
				}
			}
		}
		long total = 0;
		for (long value : answer[length])
			total += value;
		total %= MOD;
		out.printLine(total);
	}
}
