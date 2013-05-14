package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class StackingPancakes {
	long[] answer = new long[1001];
	static final int MOD = 1000000007;

	{
		long[][] count = new long[1001][1001];
		count[1][1] = 1;
		answer[1] = 1;
		for (int i = 2; i <= 1000; i++) {
			for (int j = 1; j <= i; j++) {
				count[i][j] = count[i - 1][j] * j;
				if (j != 1)
					count[i][j] += count[i - 1][j - 1];
				count[i][j] %= MOD;
				answer[i] += count[i][j];
			}
			answer[i] %= MOD;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		out.printLine(answer[count]);
	}
}
