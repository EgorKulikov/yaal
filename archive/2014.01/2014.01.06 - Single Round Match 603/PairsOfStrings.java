package net.egork;

import net.egork.numbers.IntegerUtils;

import java.util.Collections;
import java.util.List;

public class PairsOfStrings {
	static final long MOD = (long) (1e9 + 7);

    public int getNumber(int n, int k) {
		long answer = 0;
		List<Long> divisors = IntegerUtils.getDivisors(n);
		Collections.sort(divisors);
		long[] times = new long[divisors.size()];
		for (int i = divisors.size() - 1; i >= 0; i--) {
			times[i] = divisors.get(i);
			for (int j = i + 1; j < divisors.size(); j++) {
				if (divisors.get(j) % divisors.get(i) == 0)
					times[i] -= times[j];
			}
			times[i] %= MOD;
		}
		for (int i = 0; i < divisors.size(); i++) {
			answer += times[i] * IntegerUtils.power(k, divisors.get(i), MOD) % MOD;
		}
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		return (int)answer;
    }
}
