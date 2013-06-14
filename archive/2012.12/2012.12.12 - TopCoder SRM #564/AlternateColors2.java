package net.egork;

import net.egork.numbers.IntegerUtils;

public class AlternateColors2 {
	public long countWays(int n, int k) {
		long answer = 0;
		long remaining = n - k;
		if (k % 3 == 1) {
			answer += (remaining + 2) * (remaining + 1) / 2;
		}
		for (int i = 0; i < k - 1; i += 3) {
			if (((k - i) & 1) == 1)
				answer += 2 * (remaining + 1);
			answer += (((k - i - 2) >> 1)) * 2;
			answer++;
		}
		return answer;
	}
}
