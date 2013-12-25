package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PoloThePenguinAndTheNumbers {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		long result = calculate(to) - calculate(from - 1);
		if (result < 0)
			result += MOD;
		out.printLine(result);
    }

	private long calculate(int upTo) {
		long from = 1;
		long answer = 0;
		int digits = 1;
		while (from <= upTo) {
			long to = Math.min(from * 10 - 1, upTo);
			long qty = (to - from + 1) * (from + to) / 2;
			qty %= MOD;
			answer += qty * digits;
			digits++;
			from *= 10;
		}
		return answer % MOD;
	}
}
