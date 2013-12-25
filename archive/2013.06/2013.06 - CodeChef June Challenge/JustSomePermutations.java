package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JustSomePermutations {
	static final long MOD = 1000000007;

	long[] factorial = IntegerUtils.generateFactorial(1000001, MOD);

	int[][] result = new int[1 << 10][10];
	int count;
	int all;
	int sum;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxSum = in.readInt();
		maxSum -= count - 1;
		long answer = factorial[maxSum];
		int delta = count - maxSum;
		answer = (answer * IntegerUtils.power(maxSum - 1, (delta + 1) >> 1, MOD)) % MOD;
		answer = (answer * IntegerUtils.power(maxSum, delta >> 1, MOD)) % MOD;
		out.printLine(answer);
//		for (int i = 2; i <= 10; i++) {
//			count = i;
//			all = (1 << i) - 1;
//			for (int j = i + 1; j < 2 * i; j++) {
//				sum = j - 2;
//				ArrayUtils.fill(result, -1);
//				int answer = 0;
//				for (int k = 0; k < i; k++)
//					answer += go(1 << k, k);
//				out.printLine(i, j, answer);
//			}
//		}
    }

	private int go(int mask, int last) {
		if (result[mask][last] != -1)
			return result[mask][last];
		if (mask == all)
			return result[mask][last] = 1;
		result[mask][last] = 0;
		for (int i = 0; i < count && i + last <= sum; i++) {
			if ((mask >> i & 1) == 0)
				result[mask][last] += go(mask + (1 << i), i);
		}
		return result[mask][last];
	}
}
