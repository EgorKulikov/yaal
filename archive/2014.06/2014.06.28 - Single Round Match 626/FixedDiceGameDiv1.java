package net.egork;

import java.util.Arrays;

public class FixedDiceGameDiv1 {
    public double getExpectation(int a, int b, int c, int d) {
		if (a * b <= c) {
			return -1;
		}
		double[] aliceWays = getWays(a, b);
		double[] bobWays = getWays(c, d);
		double bobSum = 0;
		double weightSum = 0;
		double answer = 0;
		for (int i = 0; i < aliceWays.length; i++) {
			weightSum += bobSum * aliceWays[i];
			answer += bobSum * i * aliceWays[i];
			if (i < bobWays.length) {
				bobSum += bobWays[i];
			}
		}
		return answer / weightSum;
    }

	private double[] getWays(int times, int die) {
		double[] result = new double[times * die + 1];
		result[0] = 1;
		double[] next = new double[times * die + 1];
		for (int i = 0; i < times; i++) {
			Arrays.fill(next, 0);
			for (int j = 0; j <= i * die; j++) {
				for (int k = 1; k <= die; k++) {
					next[j + k] += result[j];
				}
			}
			double[] temp = result;
			result = next;
			next = temp;
		}
		return result;
	}
}
