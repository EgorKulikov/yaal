package on2013_11.on2013_11_20_Single_Round_Match_597.LittleElephantAndBoard;



import net.egork.numbers.IntegerUtils;

public class LittleElephantAndBoard {
	private static final long MOD = (long) (1e9 + 7);
	static long[] fac;
	static long[] rev;
	static long[] two;

    public int getNumber(int M, int R, int G, int B) {
		fac = IntegerUtils.generateFactorial(M + 1, MOD);
		rev = IntegerUtils.generateReverseFactorials(M + 1, MOD);
		two = IntegerUtils.generatePowers(2, M + 1, MOD);
		int RG = (R + G - B) / 2;
		int BR = (B + R - G) / 2;
		int GB = (G + B - R) / 2;
		if (RG < 0 || BR < 0 || GB < 0)
			return 0;
		if (RG == 0) {
			if (Math.abs(GB - BR) == 1)
				return 2;
			if (BR == GB)
				return 4;
			return 0;
		}
		int dif = Math.abs(BR - GB);
		long answer = 0;
		for (int i = dif; i <= RG + 1; i += 2) {
			int remaining = M - (RG + i);
			if (remaining < 0)
				continue;
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					if (RG - j - k > ((M - RG + i) >> 1) - 1 || i > RG + 1 - j - k || RG - j - k < 0)
						continue;
					long qty = c(((M - RG + i) >> 1) - 1, RG - j - k) * c(RG + 1 - j - k, i) % MOD * two[RG + 1 - j - k - i] % MOD * c(i, (i - dif) >> 1) % MOD;
					answer += qty;
				}
			}
		}
		return (int) (answer * 2 % MOD);
    }

	private long c(int n, int m) {
		return fac[n] * rev[m] % MOD * rev[n - m] % MOD;
	}
}
