package on2014_10.on2014_10_04_Single_Round_Match_635.ColourHolic;



import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class ColourHolic {
	private static final long MOD = (long) (1e9 + 9);

	long[] factorial = IntegerUtils.generateFactorial(100001, MOD);
	long[] reverseFactorial = IntegerUtils.generateReverseFactorials(100001, MOD);
	long[] power = IntegerUtils.generatePowers(2, 100001, MOD);

	public int countSequences(int[] n) {
		Arrays.sort(n);
		int firstHalf = n[0] + n[1];
		int secondHalf = n[2] + n[3];
		int firstDiff = n[1] - n[0];
		int secondDiff = n[3] - n[2];
		long answer = 0;
		for (int i = Math.min(firstHalf, 1); i <= firstHalf; i++) {
			for (int j = Math.max(i - 1, 1); j <= i + 1 && j <= secondHalf; j++) {
				answer += calcHalf(n[0], firstHalf, firstDiff, i) * calcHalf(n[1], secondHalf, secondDiff, j) * (i == j ? 2 : 1) % MOD;
			}
		}
		return (int) (answer % MOD);
    }

	protected long calcHalf(int small, int firstHalf, int firstDiff, int i) {
		if (i == 0) {
			return 1;
		}
		long result = 0;
		for (int k = 0; k <= small && 2 * k + firstDiff <= i; k++) {
			int special = 2 * k + firstDiff;
			int nonSpecial = i - special;
			int remaining = firstHalf - special - 2 * nonSpecial;
			if (remaining < 0) {
				continue;
			}
			remaining >>= 1;
			result += c(i, k) * c(i - k, k + firstDiff) % MOD * c(i + remaining - 1, i - 1) % MOD * power[nonSpecial] % MOD;
		}
		result %= MOD;
		return result;
	}

	private long c(int n, int k) {
		return factorial[n] * reverseFactorial[k] % MOD * reverseFactorial[n - k] % MOD;
	}
}
