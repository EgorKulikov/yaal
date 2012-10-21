package net.egork;

import net.egork.numbers.IntegerUtils;

public class XorBoard {
	private static final long MOD = 555555555;

	public int count(int H, int W, int Rcount, int Ccount, int S) {
		long answer = 0;
		long[][] c = IntegerUtils.generateBinomialCoefficients(2350, MOD);
		for (int i = Rcount % 2; i <= H && i <= Rcount; i += 2) {
			for (int j = Ccount % 2; j <= W && j <= Ccount; j += 2) {
				if (i * (W - j) + j * (H - i) == S)
					answer += c[H][i] * c[W][j] % MOD * c[H + (Rcount - i) / 2 - 1][H - 1] % MOD * c[W + (Ccount - j) / 2 - 1][W - 1] % MOD;
			}
		}
		return (int) (answer % MOD);
	}
}
