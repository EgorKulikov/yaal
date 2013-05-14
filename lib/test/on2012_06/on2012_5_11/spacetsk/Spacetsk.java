package on2012_06.on2012_5_11.spacetsk;



import net.egork.numbers.IntegerUtils;

public class Spacetsk {
	int[][] gcd = new int[2001][2001];
	final static long MOD = (long) (1e9 + 7);
	long[][] c = IntegerUtils.generateBinomialCoefficients(2000, MOD);

	{
		for (int i = 0; i <= 2000; i++)
			gcd[i][0] = gcd[0][i] = i;
		for (int i = 1; i <= 2000; i++) {
			for (int j = i; j <= 2000; j++)
				gcd[i][j] = gcd[j][i] = gcd[i][j - i];
		}
	}

	public int countsets(int L, int H, int K) {
		if (K == 1)
			return (L + 1) * (H + 1);
		long answer = 0;
		for (int i = 0; i <= L; i++) {
			for (int j = 1; j <= H; j++) {
				int possible = gcd[i][j] + 1;
				if (possible < K)
					continue;
				answer += c[possible - 1][K - 1] * (L - i + 1) * (i == 0 ? 1 : 2) % MOD;
			}
		}
		return (int) (answer % MOD);
	}


}

