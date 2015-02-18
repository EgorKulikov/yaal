package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	final static long MOD = (long) (1e9 + 7);

	static long[] factorial = IntegerUtils.generateFactorial((int) 1e6, MOD);
	static long[] reverseFactorial = IntegerUtils.generateReverseFactorials((int) 1e6, MOD);
	static long[] reverse = IntegerUtils.generateReverse((int) 1e6, MOD);
	static long[] powers = IntegerUtils.generatePowers(IntegerUtils.reverse(2, MOD), (int) 1e6, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] colors = in.readString().toCharArray();
		int changes = 0;
		for (int i = 1; i < colors.length; i++) {
			if (colors[i] == colors[0] && colors[i - 1] != colors[0]) {
				changes++;
			}
		}
		long answer;
		if (colors[0] == colors[colors.length - 1]) {
			answer = get(changes);
		} else {
			answer = 0;
			for (int i = 0; i < 2; i++) {
				long cumulative = 0;
				int current = 0;
				for (int j = 1; j < colors.length; j++) {
					if (colors[j] != colors[0] && colors[j - 1] == colors[0]) {
						cumulative += get(current) * get(changes - current) % MOD * c(changes + 1, current) % MOD;
						cumulative %= MOD;
						current++;
					}
					answer += cumulative;
				}
				answer += cumulative;
				ArrayUtils.reverse(colors);
			}
			answer %= MOD;
		}
		out.printLine(answer);
//		long[] f = new long[10];
//		f[0] = 1;
//		for (int i = 1; i < 10; i++) {
//			for (int j = 0; j < i; j++) {
//				for (int k = 0; j + k < i; k++) {
//					f[i] += f[j] * f[k] * f[i - j - k - 1] * c(i - 1, j + k) * c(j + k, j);
//				}
//			}
//		}
//		out.printLine(f);
    }

	private long c(int n, int m) {
		return factorial[n] * reverseFactorial[m] % MOD * reverseFactorial[n - m] % MOD;
	}

	private long get(int n) {
		return factorial[2 * n] * reverseFactorial[n] % MOD * powers[n] % MOD;
	}
}
