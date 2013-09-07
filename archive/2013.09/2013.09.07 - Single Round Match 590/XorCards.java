package net.egork;

import java.util.Arrays;

public class XorCards {
    public long numberOfWays(long[] number, long limit) {
		Arrays.sort(number);
		int tail = number.length - 1;
		for (int i = 49; i >= 0 && tail > 0; i--) {
			if ((number[tail] >> i & 1) == 1) {
				for (int j = 0; j < tail; j++) {
					if ((number[j] >> i & 1) == 1)
						number[j] ^= number[tail];
				}
				Arrays.sort(number, 0, tail);
				tail--;
			}
		}
		long answer = 0;
		for (int i = 49; i >= 0; i--) {
			if ((limit >> i & 1) == 1) {
				long[] copy = number.clone();
				for (int j = 0; j < copy.length; j++)
					copy[j] >>= i;
				answer += check(copy, (limit >> i) - 1);
			}
		}
		answer += check(number, limit);
		return answer;
    }

	private long check(long[] number, long limit) {
		long answer = 1;
		for (int i = number.length - 1; i >= 0; i--) {
			if (number[i] == 0)
				answer *= 2;
			else if (Long.highestOneBit(number[i]) == Long.highestOneBit(limit)) {
				limit ^= number[i];
			}
		}
		if (limit == 0)
			return answer;
		else
			return 0;
	}
}
