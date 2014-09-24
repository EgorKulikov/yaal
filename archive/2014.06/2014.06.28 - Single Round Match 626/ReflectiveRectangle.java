package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.numbers.MultiplicativeFunction;

public class ReflectiveRectangle {
	private static final long MOD = (long) (1e9 + 7);
	long rev3 = IntegerUtils.reverse(3, MOD);

	public int findSum(int sideA, int sideB, int bounces) {
		if (bounces % 2 == 1) {
			return 0;
		}
		long multiplier = ((long)sideA * sideA + (long)sideB * sideB) % MOD;
		long x = bounces + 2;
		long oX = x;
		while (oX % 2 == 0) {
			oX /= 2;
		}
		long answer = 0;
		for (long i = 1; i * i <= oX; i++) {
			if (oX % i == 0) {
				answer += get(i, x);
				if (i * i != oX) {
					answer += get(oX / i, x);
				}
			}
		}
		answer %= MOD;
		if (answer < 0) {
			answer += MOD;
		}
		return (int) (answer * multiplier % MOD);
    }

	private long get(long value, long x) {
		long mu = MultiplicativeFunction.MOBIUS.calculate(value);
		if (mu == 0) {
			return 0;
		}
		long k = x / value / 2;
		return mu * value * value % MOD * k % MOD * (2 * k - 1) % MOD * (2 * k + 1) % MOD * rev3 % MOD;
	}
}
