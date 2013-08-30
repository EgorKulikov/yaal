package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long answer = 0;
		long c = count * (count - 1) / 2;
		long[] reverse = IntegerUtils.generateReverse(count + 2, MOD);
		for (int i = 2; i + 2 <= count; i++) {
			answer += c * (i - 1) * (count - i - 1) % MOD;
			c *= (count - i);
			c %= MOD;
			c *= reverse[i + 1];
			c %= MOD;
		}
		answer %= MOD;
		answer *= reverse[2];
		out.printLine(answer % MOD);
    }
}
