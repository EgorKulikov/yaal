package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class KissesAmpHugs {

	public static final int MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long answer = (go((count + 1) / 2) + go((count + 2) / 2)) % MOD;
		out.printLine(answer);
	}

	private long go(int exponent) {
		return IntegerUtils.power(2, exponent, MOD) - 1;
	}
}
