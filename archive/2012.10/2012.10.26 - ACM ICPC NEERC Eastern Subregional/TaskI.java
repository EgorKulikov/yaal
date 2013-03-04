package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long answer = IntegerUtils.power(count, count, MOD);
		long[] helper = new long[count];
		for (int i = 1; i < count; i++) {
			helper[i] = IntegerUtils.power(i, i, MOD);
			long power = i;
			for (int j = i - 1; j > 0; j--) {
				helper[i] -= helper[j] * power % MOD;
				power *= i;
				power %= MOD;
			}
			helper[i] %= MOD;
			helper[i] += MOD;
			helper[i] %= MOD;
			answer -= IntegerUtils.power(count, count - i, MOD) * helper[i] % MOD;
		}
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		out.printLine(answer);
	}
}
