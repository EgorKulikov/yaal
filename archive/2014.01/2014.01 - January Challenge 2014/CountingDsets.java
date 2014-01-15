package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CountingDsets {
	private static final long MOD = (long) (1e9 + 7);
	long[][] c = IntegerUtils.generateBinomialCoefficients(1001, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int distance = in.readInt();
		long answer = calculate(count, distance) - calculate(count, distance - 1);
		if (answer < 0)
			answer += MOD;
		out.printLine(answer);
    }

	private long calculate(int count, int distance) {
		if (distance == 0)
			return 1;
		long result = 0;
		int multiplier = 1;
		for (int i = count; i >= 0; i--) {
			result += multiplier * c[count][i] * IntegerUtils.power(2, IntegerUtils.power(distance + 1, i, MOD - 1) * IntegerUtils.power(distance, count - i, MOD - 1) % (MOD - 1), MOD) % MOD;
			multiplier = -multiplier;
		}
		result %= MOD;
		if (result < 0)
			result += MOD;
		return result;
	}
}
