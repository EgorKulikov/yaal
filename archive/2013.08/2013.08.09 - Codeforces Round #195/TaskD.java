package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	static final long MOD = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int zero = in.readInt();
		int one = in.readInt();
		int target = in.readInt();
		if (one == 0) {
			if (zero % 2 != target)
				out.printLine(1);
			else
				out.printLine(0);
			return;
		}
		if (target == 1) {
			if (zero == 0) {
				if (one == 1)
					out.printLine(1);
				else
					out.printLine(0);
				return;
			}
			zero--;
		}
		long[] factorial = IntegerUtils.generateFactorial(zero + one, MOD);
		long[] reverseFactorial = IntegerUtils.generateReverseFactorials(Math.max(zero + 1, one), MOD);
		long result = 0;
		for (int i = zero; i > 0; i -= 2)
			result += factorial[i + one - 1] * reverseFactorial[i] % MOD;
		result %= MOD;
		result *= reverseFactorial[one - 1];
		if (zero % 2 == 1 && one == 1 || zero % 2 == 0 && one > 1)
			result++;
		result %= MOD;
		out.printLine(result);
    }
}
