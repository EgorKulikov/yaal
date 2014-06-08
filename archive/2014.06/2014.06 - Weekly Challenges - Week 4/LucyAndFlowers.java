package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LucyAndFlowers {
	long[] answer = new long[5001];
	long[] useLast = new long[5001];

	private static final long MOD = (long) (1e9 + 9);

	{
		answer[0] = 1;
		useLast[0] = 1;
		for (int i = 1; i <= 5000; i++) {
			for (int j = 0; j < i; j++)
				useLast[i] += answer[j] * useLast[i - j - 1] % MOD;
			useLast[i] %= MOD;
			answer[i] = (answer[i - 1] + useLast[i]) % MOD;
		}
	}


    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long result = answer[count] - 1;
		if (result < 0)
			result += MOD;
		out.printLine(result);
    }
}
