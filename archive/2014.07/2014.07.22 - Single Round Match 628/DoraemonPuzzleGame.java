package net.egork;

import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class DoraemonPuzzleGame {
    public double solve(int[] X, int[] Y, int m) {
		int count = X.length;
		double[] value = new double[count];
		for (int i = 0; i < count; i++) {
			value[i] = (double)Y[i] / (X[i] + Y[i]);
		}
		int[] order = ArrayUtils.order(value);
		double[] probability = new double[count + 1];
		double[] expected = new double[count + 1];
		probability[0] = 1;
		double[] nextProbability = new double[count + 1];
		double[] nextExpected = new double[count + 1];
		int remaining = count;
		m -= count;
		for (int i : order) {
			Arrays.fill(nextProbability, 0);
			Arrays.fill(nextExpected, 0);
			for (int j = 0; j < count; j++) {
				if (j + remaining < m) {
					continue;
				}
				if (j + remaining == m) {
					nextProbability[j + 1] += probability[j];
					nextExpected[j + 1] += expected[j] + 1000d / Y[i] * probability[j];
					continue;
				}
				double ratio = ((double) X[i]) / (X[i] + Y[i]);
				double wait = 1000d / (X[i] + Y[i]);
				nextProbability[j] += probability[j] * ratio;
				nextExpected[j] += (expected[j] + wait * probability[j]) * ratio;
				nextProbability[j + 1] += probability[j] * (1 - ratio);
				nextExpected[j + 1] += (expected[j] + wait * probability[j]) * (1 - ratio);
			}
			remaining--;
			double[] temp = expected;
			expected = nextExpected;
			nextExpected = temp;
			temp = probability;
			probability = nextProbability;
			nextProbability = temp;
		}
		double answer = 0;
		for (int i = m; i <= count; i++) {
			answer += expected[i];
		}
		return answer;
    }
}
