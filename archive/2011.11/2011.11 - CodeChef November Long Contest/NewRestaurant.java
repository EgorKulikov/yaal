package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class NewRestaurant {
	private long[][] count = new long[1001][1001];
	private static final long MOD = 1000000007;
	private long[] factorial = IntegerUtils.generateFactorial(1000001, MOD);

	{
		count[0][0] = 1;
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= 1000; j++)
				count[i][j] = (count[i - 1][j - 1] + count[i - 1][j] * j) % MOD;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int dayCount = in.readInt();
		int typeCount = in.readInt();
		int maxTypes = in.readInt();
		long result = 0;
		for (int i = 1; i <= maxTypes && i <= typeCount; i++) {
			long current = count[dayCount][i];
			current *= factorial[typeCount];
			current %= MOD;
			current *= IntegerUtils.reverse(factorial[typeCount - i], MOD);
			current %= MOD;
			result += current;
		}
		result %= MOD;
		out.printLine(result);
	}
}
