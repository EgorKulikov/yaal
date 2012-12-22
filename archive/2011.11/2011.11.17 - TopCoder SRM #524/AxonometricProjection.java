package net.egork;

import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class AxonometricProjection {
	private static final long MOD = 1000000009;
	private static final long[][] c = IntegerUtils.generateBinomialCoefficients(51, MOD);

	public int howManyWays(int[] heightsOfLeftView, int[] heightsOfFrontView) {
		int rowProcessed = 0;
		int columnProcessed = 0;
		long answer = 1;
		for (int i = 10000; i >= 0; i--) {
			int currentColumns = 0;
			int currentRows = 0;
			for (int j : heightsOfFrontView) {
				if (j == i)
					currentRows++;
			}
			for (int j : heightsOfLeftView) {
				if (j == i)
					currentColumns++;
			}
			if (currentColumns != 0 || currentRows != 0) {
				answer = answer * go(rowProcessed, columnProcessed, currentRows, currentColumns, i) % MOD;
				rowProcessed += currentRows;
				columnProcessed += currentColumns;
			}
		}
		return (int) answer;
	}

	private long go(int rowProcessed, int columnProcessed, int currentRows, int currentColumns, int height) {
		long[] powerButOne = new long[currentColumns + columnProcessed + 1];
		powerButOne[0] = 1;
		for (int i = 1; i <= currentColumns + columnProcessed; i++)
			powerButOne[i] = powerButOne[i - 1] * height % MOD;
		long[] power = new long[currentColumns + 1 + columnProcessed];
		power[0] = 1;
		for (int i = 1; i <= currentColumns + columnProcessed; i++)
			power[i] = power[i - 1] * (height + 1) % MOD;
		long[] count = new long[currentColumns + 1];
		count[currentColumns] = 1;
		long[] nextCount = new long[currentColumns + 1];
		for (int i = 0; i < rowProcessed; i++) {
			Arrays.fill(nextCount, 0);
			for (int j = 0; j <= currentColumns; j++) {
				for (int k = j; k <= currentColumns; k++)
					nextCount[j] += count[k] * c[k][j] % MOD * powerButOne[j] % MOD * power[currentColumns - k] % MOD;
				nextCount[j] %= MOD;
			}
			long[] temp = count;
			count = nextCount;
			nextCount = temp;
		}
		for (int i = 0; i < currentRows; i++) {
			Arrays.fill(nextCount, 0);
			for (int j = 0; j <= currentColumns; j++) {
				for (int k = j + 1; k <= currentColumns; k++)
					nextCount[j] += count[k] * c[k][j] % MOD * powerButOne[j] % MOD * power[currentColumns + columnProcessed - k] % MOD;
				for (int k = 1; k <= currentColumns - j + columnProcessed; k++)
					nextCount[j] += count[j] * c[currentColumns + columnProcessed - j][k] % MOD * powerButOne[currentColumns + columnProcessed - k] % MOD;
				nextCount[j] %= MOD;
			}
			long[] temp = count;
			count = nextCount;
			nextCount = temp;
		}
		return count[0];
	}
}

