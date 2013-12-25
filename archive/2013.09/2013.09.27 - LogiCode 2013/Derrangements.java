package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Derrangements {
	public static final int MOD = 1000000007;
	long[] factorials = IntegerUtils.generateFactorial(100001, MOD);
	long[] reverse = IntegerUtils.generateReverseFactorials(100001, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int notFixed = in.readInt();
		int sign = 1;
		long answer = 0;
		for (int i = 0; i <= notFixed; i++) {
			answer += sign * factorials[count - i] * c(notFixed, i) % MOD;
			sign = -sign;
		}
		answer %= MOD;
		if (answer < 0)
			answer += MOD;
		out.printLine(answer);
    }

	final long c(int n, int m) {
		return factorials[n] * reverse[m] % MOD * reverse[n - m] % MOD;
	}
}
