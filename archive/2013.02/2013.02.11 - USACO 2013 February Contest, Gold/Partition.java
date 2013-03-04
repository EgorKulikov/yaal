package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Partition {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int dividerCount = in.readInt();
		int[][] cows = IOUtils.readIntTable(in, size, size);
		int[][] result = new int[size + 1][size + 1];
		int[][] qty = new int[size + 1][size + 1];
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++)
				qty[i][j] = cows[i - 1][j - 1] + qty[i - 1][j] + qty[i][j - 1] - qty[i - 1][j - 1];
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 1 << (size - 1); i++) {
			if (Integer.bitCount(i) > dividerCount)
				continue;
			int remaining = Math.min(dividerCount - Integer.bitCount(i), size - 1) + 1;
			for (int j = 1; j <= size; j++) {
				Arrays.fill(result[j], Integer.MAX_VALUE);
				for (int l = 0; l < j; l++) {
					int current = 0;
					int last = 0;
					for (int k = 1; k <= size; k++) {
						if (k == size || (i >> (k - 1) & 1) == 1) {
							current = Math.max(current, qty[j][k] - qty[j][last] - qty[l][k] + qty[l][last]);
							last = k;
						}
					}
					for (int k = 1; k <= remaining; k++) {
						result[j][k] = Math.min(result[j][k], Math.max(result[l][k - 1], current));
					}
				}
			}
			answer = Math.min(answer, result[size][remaining]);
		}
		out.printLine(answer);
	}
}
