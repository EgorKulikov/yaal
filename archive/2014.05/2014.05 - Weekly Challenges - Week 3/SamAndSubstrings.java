package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SamAndSubstrings {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		long qty = 0;
		long sum = 0;
		long answer = 0;
		for (char c : s) {
			qty++;
			sum = (sum * 10 + (c - '0') * qty) % MOD;
			answer += sum;
		}
		out.printLine(answer % MOD);
    }
}
