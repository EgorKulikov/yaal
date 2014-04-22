package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.MultiplicativeFunction;

public class RandomGCD {
	private static final long MOD = (long) (1e9 + 7);

	public int countTuples(int N, int K, int low, int high) {
		low = (low + K - 1) / K;
		high = high / K;
		if (low > high)
			return 0;
		long answer = 0;
		long[] mu = MultiplicativeFunction.MOBIUS.calculateUpTo(high - low + 1);
		for (int i = 1; i <= high - low; i++) {
			if (mu[i] == 0)
				continue;
			int from = (low + i - 1) / i;
			int to = high / i;
			if (to - from >= 0)
				answer += mu[i] * IntegerUtils.power(to - from + 1, N, MOD);
		}
		int[] m = new int[high - low + 1];
		if (low == 1)
			m[0] = -1;
		for (int i = 1; i <= high - low; i++) {
			if (mu[i] == 0)
				continue;
			int start = low + (i - low % i) % i;
			for (int j = start; j <= high; j += i) {
				m[j - low] += mu[i];
			}
		}
		answer -= ArrayUtils.sumArray(m);
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		return (int) answer;
    }
}
