package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.numbers.IntegerUtils;

public class DengklekCountingFormations {
	long[][][][] result;
	static final long MOD = (long)1e9 + 7;
	static final long[][] c = IntegerUtils.generateBinomialCoefficients(51, MOD);
	static final long[][][] cPow = new long[51][51][11];

	static {
		for (int i = 0; i <= 50; i++) {
			for (int j = 0; j <= i; j++) {
				cPow[i][j][0] = 1;
				for (int k = 1; k <= 10; k++)
					cPow[i][j][k] = cPow[i][j][k - 1] * c[i][j] % MOD;
			}
		}
	}

	public int numFormations(int N, int M, int K) {
		result = new long[K + 1][N + 1][M + 1][M + 1];
		ArrayUtils.fill(result, -1);
		return (int) go(K, N, M, 0);
	}

	private long go(int types, int rows, int columns, int minTake) {
		if (rows == 0)
			return 1;
		if (columns == 0)
			return rows == 1 ? 1 : 0;
		if (types == 0)
			return 0;
		if (minTake > columns)
			return 0;
		if (result[types][rows][columns][minTake] != -1)
			return result[types][rows][columns][minTake];
		result[types][rows][columns][minTake] = go(types, rows, columns, minTake + 1);
		for (int i = 1; i <= rows; i++)
			result[types][rows][columns][minTake] += c[rows][i] * cPow[columns][minTake][i] % MOD *
				go(types - 1, i, columns - minTake, 0) % MOD *
				go(types, rows - i, columns, minTake + 1) % MOD;
		return result[types][rows][columns][minTake] %= MOD;
	}
}

