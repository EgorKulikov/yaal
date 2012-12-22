package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Combinations {
	final long[] answer = new long[1000001];

	private static final long MOD = (long)1e9 + 7;

	{
		answer[0] = answer[1] = 1;
		long[] reverse = new long[1000001];
		reverse[1] = 1;
		for (int i = 2; i <= 1000000; i++) {
			reverse[i] = (MOD - (MOD / i) * reverse[((int) (MOD % i))] % MOD) % MOD;
			answer[i] = answer[i - 1];
			answer[i] *= 4 * i - 2;
			answer[i] %= MOD;
			answer[i] *= reverse[i - 1];
			answer[i] %= MOD;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(answer[in.readInt()]);
	}
}
