package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CardGame {
	static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		int[] strengths = IOUtils.readIntArray(in, count);
		long current = 1;
		ArrayUtils.sort(strengths, IntComparator.DEFAULT);
		long answer = 0;
		for (int i = size - 1; i < count; i++) {
			answer += current * strengths[i] % MOD;
			current *= i + 1;
			current %= MOD;
			current *= IntegerUtils.reverse(i - size + 2, MOD);
			current %= MOD;
		}
		answer %= MOD;
		out.printLine("Case #" + testNumber + ":", answer);
	}
}
