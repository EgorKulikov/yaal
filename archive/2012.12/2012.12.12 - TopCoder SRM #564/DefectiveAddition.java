package net.egork;

import net.egork.numbers.IntegerUtils;

public class DefectiveAddition {
	private static final long MOD = (long) (1e9 + 7);

	public int count(int[] cards, int n) {
		int max = 0;
		for (int i : cards)
			max = Math.max(max, i + 1);
		int power = Integer.highestOneBit(max);
		if (n / power > 1)
			return 0;
		long[][] answer = new long[2][2];
		answer[0][0] = 1;
		int total = 0;
		for (int i : cards) {
			total ^= i + 1;
			long[][] next = new long[2][2];
			if (i + 1 >= power) {
				for (int j = 0; j < 2; j++) {
					next[j][0] = answer[1 - j][0] * (i - power + 1) % MOD;
					next[j][1] = (answer[1 - j][1] * (i - power + 1) + answer[j][0] + power * answer[j][1]) % MOD;
				}
			} else {
				for (int j = 0; j < 2; j++) {
					next[j][0] = answer[j][0] * (i + 1) % MOD;
					next[j][1] = answer[j][1] * (i + 1) % MOD;
				}
			}
			answer = next;
		}
		int result = (int) answer[n / power][1];
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] == power - 1)
				return result;
			cards[i] &= power - 1;
		}
		if (total / power == n / power)
			result += count(cards, n & (power - 1));
//		return (int) answer[n / power][1] + count()
//		return (int) (answer[n / power] * IntegerUtils.reverse(power, MOD) % MOD);
		return (int) (result % MOD);
	}
}
