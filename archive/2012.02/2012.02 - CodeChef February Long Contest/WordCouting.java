package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class WordCouting {
	static final long MOD = (long)1e9 + 7;
	static final long[] factorials = IntegerUtils.generateFactorial(501, MOD);
	static final long[] reverseFactorials = IntegerUtils.generateReverseFactorials(11, MOD);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] word = in.readString().toCharArray();
		int[] count = new int[256];
		for (char c : word)
			count[c]++;
		int total = (int) ArrayUtils.sumArray(count);
		long result = factorials[total];
		for (int i : count)
			result = result * reverseFactorials[i] % MOD;
		out.printLine(result);
	}
}
