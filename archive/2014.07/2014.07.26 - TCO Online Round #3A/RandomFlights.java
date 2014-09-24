package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class RandomFlights {
	int count;
	double[] answer;
	double[][] present;
	double[][] distance;

    public double expectedDistance(int[] x, int[] y, String[] flight) {
		count = x.length;
		distance = new double[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				distance[i][j] = Math.hypot(x[i] - x[j], y[i] - y[j]);
			}
		}
		present = new double[count][count];
		ArrayUtils.fill(present, Double.POSITIVE_INFINITY);
		for (int i = 0; i < count; i++) {
			present[i][i] = 0;
			for (int j = 0; j < count; j++) {
				if (flight[i].charAt(j) == '1') {
					present[i][j] = distance[i][j];
				}
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					present[j][k] = Math.min(present[j][k], present[j][i] + present[i][k]);
				}
			}
		}
		if (!Double.isInfinite(present[0][1])) {
			return present[0][1];
		}
		int start = 1;
		double mean = 0;
		for (int i = 1; i < count; i++) {
			if (!Double.isInfinite(present[0][i])) {
				start += 1 << i;
				mean += present[0][i];
			}
		}
		mean /= Integer.bitCount(start);
		answer = new double[1 << count];
		Arrays.fill(answer, -1);
		return mean + go(start);
    }

	private double go(int mask) {
		if (answer[mask] != -1) {
			return answer[mask];
		}
		int currentIn = Integer.bitCount(mask);
		answer[mask] = 0;
		for (int i = 0; i < count; i++) {
			if ((mask >> i & 1) == 0) {
				double meanTo = 0;
				for (int j = 0; j < count; j++) {
					if ((mask >> j & 1) == 1) {
						meanTo += distance[i][j];
					}
				}
				meanTo /= currentIn;
				if (!Double.isInfinite(present[i][1])) {
					answer[mask] += meanTo + present[i][1];
				} else {
					int nextMask = mask;
					double inside = 0;
					for (int j = 0; j < count; j++) {
						if (!Double.isInfinite(present[i][j])) {
							inside += present[i][j];
							nextMask += 1 << j;
						}
					}
					int nextIn = Integer.bitCount(nextMask);
					answer[mask] += ((nextIn - currentIn) * meanTo + inside) / nextIn + go(nextMask);
				}
			}
		}
		return answer[mask] /= count - currentIn;
	}
}
