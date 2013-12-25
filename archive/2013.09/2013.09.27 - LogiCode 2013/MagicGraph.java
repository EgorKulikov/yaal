package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MagicGraph {
	static final long MOD = 1000000007;

	long[] answer = new long[1000001];

	{
		answer[1] = 1;
		long connected = 1;
		long s0 = 0;
		long s2 = 1;
		long two = 1;
		for (int i = 3; i <= 1000001; i++) {
			answer[i - 1] = connected * (s0 + s2) % MOD;
			two = (2 * two + 1) % MOD;
			connected = connected * two % MOD;
			long nS0 = s2;
			long nS2 = ((long)(i - 1) * (i - 2) / 2 % MOD * s0 + s0 * (i - 1) + 2 * s2 + (i - 3) * s2 * 3 +
				(long)(i - 3) * (i - 4) / 2 % MOD * s2) % MOD;
			s0 = nS0;
			s2 = nS2;
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		out.printLine(answer[size]);
    }
}
