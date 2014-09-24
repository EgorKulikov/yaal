package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;

public class NegativeGraphDiv1 {
	long[][] zero;
	long[][] one;

    public long findMin(int N, int[] from, int[] to, int[] weight, int charges) {
		MiscUtils.decreaseByOne(from, to);
		zero = new long[N][N];
		ArrayUtils.fill(zero, Long.MAX_VALUE / 2);
		for (int i = 0; i < N; i++) {
			zero[i][i] = 0;
		}
		for (int i = 0; i < from.length; i++) {
			zero[from[i]][to[i]] = Math.min(zero[from[i]][to[i]], weight[i]);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					zero[j][k] = Math.min(zero[j][k], zero[j][i] + zero[i][k]);
				}
			}
		}
		one = new long[N][N];
		for (int i = 0; i < N; i++) {
			System.arraycopy(zero[i], 0, one[i], 0, N);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < from.length; k++) {
					one[i][j] = Math.min(one[i][j], zero[i][from[k]] + zero[to[k]][j] - weight[k]);
				}
			}
		}
		long[][] result = new long[N][N];
		long[][] temp = new long[N][N];
		power(charges, result, temp);
		return result[0][N - 1];
    }

	private void power(int exponent, long[][] result, long[][] temp) {
		if (exponent == 0) {
			for (int i = 0; i < result.length; i++) {
				System.arraycopy(zero[i], 0, result[i], 0, result[i].length);
			}
			return;
		}
		if ((exponent & 1) == 0) {
			power(exponent >> 1, temp, result);
			multiply(result, temp, temp);
		} else {
			power(exponent - 1, temp, result);
			multiply(result, temp, one);
		}
	}

	private void multiply(long[][] result, long[][] first, long[][] second) {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				result[i][j] = Long.MAX_VALUE >> 1;
				for (int k = 0; k < result.length; k++) {
					result[i][j] = Math.min(result[i][j], first[i][k] + second[k][j]);
				}
			}
		}
	}
}
