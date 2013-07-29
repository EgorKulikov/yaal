package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	static final long MOD = (long) (2e9 + 13);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int placeCount = in.readInt();
		int count = in.readInt();
		int[] height = IOUtils.readIntArray(in, count);
		long[][][] variants = new long[count][1 << count][];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < (1 << count); j++) {
				if ((j >> i & 1) != 0)
					variants[i][j] = new long[placeCount + 1];
			}
		}
		for (int i = 0; i < count; i++)
			variants[i][1 << i][1] = 1;
		for (int i = 0; i < (1 << count); i++) {
			for (int j = 0; j < count; j++) {
				if (variants[j][i] == null)
					continue;
				for (int k = 0; k < count; k++) {
					if ((i >> k & 1) != 0)
						continue;
					int delta = Math.max(1, height[j] - height[k] + 1);
					for (int l = 0; l <= placeCount - delta; l++) {
						variants[k][i + (1 << k)][l + delta] += variants[j][i][l];
						if (variants[k][i + (1 << k)][l + delta] >= MOD)
							variants[k][i + (1 << k)][l + delta] -= MOD;
					}
				}
			}
		}
		long answer = 0;
		long[][] c = IntegerUtils.generateBinomialCoefficients(placeCount + 1, MOD);
		for (int i = 0; i < count; i++) {
			int j = (1 << count) - 1;
				if (variants[i][j] == null)
					continue;
				int bits = Integer.bitCount(j);
				for (int k = 0; k <= placeCount; k++) {
					if (variants[i][j][k] == 0)
						continue;
					answer += variants[i][j][k] * c[bits + placeCount - k][bits] % MOD;
				}
		}
		answer %= MOD;
		out.printLine(answer);
    }
}
