package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private static final long[][] count = new long[101][100 * 25 + 1];

	private static final long MOD = (long)1e9 + 7;

	static {
		count[0][0] = 1;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j <= 2500; j++) {
				for (int k = 0; k <= 25 && k <= j; k++)
					count[i + 1][j] += count[i][j - k];
				count[i + 1][j] %= MOD;
				if (count[i + 1][j] == 0 && j <= 25 * (i + 1)) {
					System.err.println(i + 1 + " " + j);
				}
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int sum = 0;
		for (char c : s)
			sum += c - 'a';
		out.printLine((count[s.length][sum] + MOD - 1) % MOD);
	}
}
