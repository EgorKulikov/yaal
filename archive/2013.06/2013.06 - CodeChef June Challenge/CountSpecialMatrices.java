package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CountSpecialMatrices {
	static final int MOD = 1000000007;
	static final long REV2 = IntegerUtils.reverse(2, MOD);

	long[] answer = new long[(int) (1e7 + 1)];

	{
		long sumT = 0;
		long c2 = REV2;
		for (int n = 3; n < answer.length; n += 2) {
			answer[n] = (sumT + (n - 1) * c2);
			sumT = (sumT * (n - 2) + 2 * answer[n]) % MOD * ((n + 1) >> 1) % MOD;
			c2 = c2 * ((n + 1) >> 1) % MOD * n % MOD;
			answer[n + 1] = (sumT + n * c2);
			sumT = (sumT * ((n - 1) >> 1) + answer[n + 1]) % MOD * (n + 2) % MOD;
			c2 = c2 * ((n + 1) >> 1) % MOD * (n + 2) % MOD;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(answer[in.readInt()] % MOD);
    }
}
