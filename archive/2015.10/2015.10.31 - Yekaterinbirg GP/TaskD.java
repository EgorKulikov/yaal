package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long[] answer = new long[n + 1];
		long[] sum = new long[n + 1];
		answer[1] = 1;
		sum[1] = 1;
		for (int i = 2; i <= n; i++) {
			answer[i] = (1 + sum[i / 2]) % MOD;
			sum[i] = (sum[i - 1] + answer[i]) % MOD;
		}
		out.printLine(answer[n]);
	}
}
