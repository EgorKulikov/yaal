package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long a = in.readLong();
		long b = in.readLong();
		long answer = b * (b - 1) / 2 % MOD * a + a * (a + 1) / 2 % MOD * (b * (b - 1) / 2 % MOD) % MOD * b;
		answer %= MOD;
		out.printLine(answer);
    }
}
