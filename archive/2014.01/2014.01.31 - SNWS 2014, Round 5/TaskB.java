package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	private static final long MOD = (long) (1e9 + 7);
	private long[] factorial;
	private long[] reverse;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[][] masks = new char[3][];
		for (int i = 0; i < 3; i++)
			masks[i] = in.readString().toCharArray();
		int[] count = new int[3];
		int power = 0;
		for (int i = 0; i < masks[0].length; i++) {
			if (masks[0][i] == masks[1][i] && masks[0][i] == masks[2][i]) {
				power++;
				continue;
			}
			for (int j = 0; j < 3; j++) {
				if (masks[j][i] != masks[(j + 1) % 3][i] && masks[j][i] != masks[(j + 2) % 3][i])
					count[j]++;
			}
		}
		if (count[0] % 2 != count[1] % 2 || count[0] % 2 != count[2] % 2) {
			out.printLine(0);
			return;
		}
		long answer = 0;
		int length = ArrayUtils.maxElement(count);
		factorial = IntegerUtils.generateFactorial(length + 1, MOD);
		reverse = IntegerUtils.generateReverseFactorials(length + 1, MOD);
		int maxDelta = ArrayUtils.minElement(count);
		for (int i = maxDelta & 1; i <= maxDelta; i += 2) {
			answer += c(count[0], (count[0] - i) >> 1) * c(count[1], (count[1] - i) >> 1) % MOD *
				c(count[2], (count[2] - i) >> 1) % MOD * (i == 0 ? 1 : 2) % MOD;
		}
		answer %= MOD;
		answer *= IntegerUtils.power(2, power, MOD);
		answer %= MOD;
		out.printLine(answer);
    }

	private long c(int n, int m) {
		return factorial[n] * reverse[m] % MOD * reverse[n - m] % MOD;
	}
}
