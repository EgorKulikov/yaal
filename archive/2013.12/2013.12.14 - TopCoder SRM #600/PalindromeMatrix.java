package net.egork;

import net.egork.misc.ArrayUtils;

public class PalindromeMatrix {
    public int minChange(String[] A, int rowCount, int columnCount) {
		int n = A.length;
		int m = A[0].length();
		int[][] table = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				table[i][j] = A[i].charAt(j) - '0';
		}
		int halfRows = n / 2;
		int halfColumns = m / 2;
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < (1 << m); i++) {
			if (Integer.bitCount(i) != columnCount)
				continue;
			int[][] cost = new int[3][halfRows];
			ArrayUtils.fill(cost, Integer.MAX_VALUE);
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < halfRows; k++) {
					int oppRow = n - k - 1;
					int curCost = 0;
					for (int l = 0; l < halfColumns; l++) {
						int oppColumn = m - l - 1;
						if ((i >> l & 1) == 1) {
							if ((i >> oppColumn & 1) == 1) {
								if (j != 0) {
									int sum = table[k][l] + table[k][oppColumn] + table[oppRow][l] + table[oppRow][oppColumn];
									curCost += Math.min(sum, 4 - sum);
								} else {
									int sum = table[k][l] + table[oppRow][l];
									curCost += Math.min(sum, 2 - sum);
									sum = table[k][oppColumn] + table[oppRow][oppColumn];
									curCost += Math.min(sum, 2 - sum);
								}
							} else {
								if (j == 0) {
									int sum = table[k][l] + table[oppRow][l];
									curCost += Math.min(sum, 2 - sum);
								} else if (j == 1) {
									int sum = table[k][l] + table[oppRow][l] + table[k][oppColumn];
									curCost += Math.min(sum, 3 - sum);
								} else if (j == 2) {
									int sum = table[k][l] + table[oppRow][l] + table[oppRow][oppColumn];
									curCost += Math.min(sum, 3 - sum);
								} else {
									int sum = table[k][l] + table[k][oppColumn] + table[oppRow][l] + table[oppRow][oppColumn];
									curCost += Math.min(sum, 4 - sum);
								}
							}
						} else {
							if ((i >> oppColumn & 1) == 1) {
								if (j == 0) {
									int sum = table[k][oppColumn] + table[oppRow][oppColumn];
									curCost += Math.min(sum, 2 - sum);
								} else if (j == 1) {
									int sum = table[k][l] + table[oppRow][oppColumn] + table[k][oppColumn];
									curCost += Math.min(sum, 3 - sum);
								} else if (j == 2) {
									int sum = table[k][oppColumn] + table[oppRow][l] + table[oppRow][oppColumn];
									curCost += Math.min(sum, 3 - sum);
								} else {
									int sum = table[k][l] + table[k][oppColumn] + table[oppRow][l] + table[oppRow][oppColumn];
									curCost += Math.min(sum, 4 - sum);
								}
							} else {
								if ((j & 1) == 1) {
									int sum = table[k][oppColumn] + table[k][l];
									curCost += Math.min(sum, 2 - sum);
								}
								if ((j & 2) == 2) {
									int sum = table[oppRow][oppColumn] + table[oppRow][l];
									curCost += Math.min(sum, 2 - sum);
								}
							}
						}
					}
					cost[Integer.bitCount(j)][k] = Math.min(cost[Integer.bitCount(j)][k], curCost);
				}
			}
			int[][] result = new int[halfRows + 1][n + 1];
			ArrayUtils.fill(result, Integer.MAX_VALUE);
			result[0][0] = 0;
			for (int j = 0; j < halfRows; j++) {
				for (int k = 0; k <= 2 * j; k++) {
					for (int l = 0; l <= 2; l++)
						result[j + 1][k + l] = Math.min(result[j + 1][k + l], result[j][k] + cost[l][j]);
				}
			}
			for (int j = rowCount; j <= n; j++)
				answer = Math.min(answer, result[halfRows][j]);
		}
		return answer;
    }
}
