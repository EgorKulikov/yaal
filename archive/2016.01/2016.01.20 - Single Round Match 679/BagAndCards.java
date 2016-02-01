package net.egork;

import java.util.Arrays;

public class BagAndCards {
	private static final long MOD = (long) (1e9 + 7);

	public int getHash(int n, int m, int x, int a, int b, int c, String isGood) {
		int[][] count = new int[n][m];
		long limit = Long.MAX_VALUE - MOD * MOD;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				count[i][j] = x;
				x = (int) ((((long)x * a + b) ^ c) % MOD);
			}
		}
		int answer = 0;
		long[] delta = new long[m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(delta, 0);
			for (int j = 0; j < m; j++) {
				for (int l = 0; l < m; l++) {
					if (isGood.charAt(j + l) == 'Y') {
						delta[l] += count[i][j];
					}
				}
			}
			for (int j = 0; j < m; j++) {
				delta[j] %= MOD;
			}
			for (int j = i + 1; j < n; j++) {
				long current = 0;
				for (int k = 0; k < m; k++) {
					current += delta[k] * count[j][k];
					if (current >= limit) {
						current %= MOD;
					}
				}
				current %= MOD;
				answer ^= current;
			}
		}
		return answer;
	}
}
