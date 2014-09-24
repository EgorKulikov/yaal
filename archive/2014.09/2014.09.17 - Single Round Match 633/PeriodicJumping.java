package net.egork;

import java.util.Arrays;

public class PeriodicJumping {
    public int minimalTime(int x, int[] jumpLengths) {
		if (x == 0) {
			return 0;
		}
		jumpLengths = Arrays.copyOf(jumpLengths, jumpLengths.length * 2);
		System.arraycopy(jumpLengths, 0, jumpLengths, jumpLengths.length >> 1, jumpLengths.length >> 1);
		x = Math.abs(x);
		long sum = 0;
		long diff = 0;
		int answer = 0;
		for (int i : jumpLengths) {
			answer++;
			diff = Math.max(i - sum, diff - i);
			sum += i;
			if (x >= diff && x <= sum) {
				return answer;
			}
		}
		answer = (int) (x / sum * jumpLengths.length);
		x %= sum;
		for (int i : jumpLengths) {
			if (x <= 0) {
				break;
			}
			x -= i;
			answer++;
		}
		return answer;
    }
}
