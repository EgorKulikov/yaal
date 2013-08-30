package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheNumberOfSolutions {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int upper = in.readInt();
		int power = in.readInt();
		int sum = in.readInt();
		int modulo = in.readInt();
		int[] value = new int[modulo];
		long[] qty = new long[modulo];
		for (int i = 0; i < modulo; i++) {
			value[i] = (int) IntegerUtils.power(i, power, modulo);
			qty[i] = upper / modulo + (upper % modulo >= i ? 1 : 0);
		}
		long answer = 0;
		for (int i = 0; i < modulo; i++) {
			for (int j = 0; j < modulo; j++) {
				for (int k = 0; k < modulo; k++) {
					if ((value[i] + value[j] + value[k]) % modulo == sum)
						answer += qty[i] * qty[j] % MOD * qty[k] % MOD;
				}
			}
		}
		answer %= MOD;
		out.printLine(answer);
	}
}
