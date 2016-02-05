package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Retract {
	private static final long MOD = (long) (1e9 + 7);

	long[] fact = IntegerUtils.generateFactorial(1000001, MOD);
	long[] rev = IntegerUtils.generateReverseFactorials(1000001, MOD);

	long c(int n, int m) {
		if (m < 0 || m > n) {
			return 0;
		}
		return fact[n] * rev[m] % MOD * rev[n - m] % MOD;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int[] b = IOUtils.readIntArray(in, n);
		int[] p = new int[n];
		long answer = 0;
		for (int i = 0; i < fact[n]; i++) {
			boolean[] used = new boolean[n];
			int oddity = 0;
			for (int j = 0; j < n; j++) {
				int at = (int) (i % fact[n -j] / fact[n - j - 1]);
				for (int l = 0; l < n; l++) {
					if (!used[l]) {
						at--;
					}
					if (at < 0) {
						p[j] = l;
						used[l] = true;
						for (int m = l + 1; m < n; m++) {
							if (used[m]) {
								oddity++;
							}
						}
						break;
					}
				}
			}
			long current = 1;
			for (int j = 0; j < n; j++) {
				current *= c(k, a[p[j]] - b[j]);
				current %= MOD;
			}
			if (oddity % 2 == 0) {
				answer += current;
			} else {
				answer -= current;
			}
		}
		answer %= MOD;
		if (answer < 0) {
			answer += MOD;
		}
		out.printLine(answer);
	}
}
