package net.egork;

import net.egork.collections.ArrayUtils;

public class BricksN {
	private static final int MOD = 1000000007;
	private long[] successiveWays;
	private long[][] result;

	public int countStructures(int w, int h, int k) {
		successiveWays = new long[w + 1];
		successiveWays[0] = 1;
		for (int i = 1; i <= w; i++) {
			for (int j = 1; j <= k && j <= i; j++)
				successiveWays[i] += successiveWays[i - j];
			successiveWays[i] %= MOD;
		}
		result = new long[w + 1][h + 1];
		ArrayUtils.fill(result, -1);
		return (int) go(w, h);
	}

	private long go(int w, int h) {
		if (result[w][h] != -1)
			return result[w][h];
		if (h == 0 || w == 0)
			return 1;
		result[w][h] = 0;
		for (int i = 0; i <= w; i++) {
			result[w][h] += go(i, h - 1) * go(Math.max(w - i - 1, 0), h) % MOD * successiveWays[i] % MOD;
		}
		return result[w][h] %= MOD;
	}


}

