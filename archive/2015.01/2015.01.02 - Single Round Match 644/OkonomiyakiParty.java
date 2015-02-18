package net.egork;

import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class OkonomiyakiParty {
	private static final long MOD = (long) (1e9 + 7);

	public int count(int[] osize, int M, int K) {
		long[][] c = IntegerUtils.generateBinomialCoefficients(osize.length + 1);
		Arrays.sort(osize);
		long answer = 0;
		for (int i = 0; i < osize.length; i++) {
			for (int j = i + 1; j < osize.length; j++) {
				if (osize[j] - osize[i] > K) {
					break;
				}
				if (j - i >= M - 1) {
					answer += c[j - i - 1][M - 2];
				}
			}
		}
		return (int) (answer % MOD);
    }
}
