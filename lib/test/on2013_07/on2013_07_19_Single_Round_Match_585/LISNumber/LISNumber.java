package on2013_07.on2013_07_19_Single_Round_Match_585.LISNumber;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class LISNumber {
	private static final long MOD = (long) (1e9 + 7);
	public static final int MAX = 36 * 36;

	public int count(int[] cardsnum, int K) {
		long[] answer = new long[MAX + 2];
		long[] next = new long[MAX + 2];
		answer[K] = 1;
		long[][] c = IntegerUtils.generateBinomialCoefficients(MAX + 37, MOD);
		int places = (int) (ArrayUtils.sumArray(cardsnum) + 1);
		ArrayUtils.reverse(cardsnum);
		long[] coef = new long[37];
		for (int i : cardsnum) {
			places -= i;
			Arrays.fill(next, 0);
			for (int j = 0; j <= places; j++) {
				for (int k = 0; k <= i; k++)
					coef[k] = c[j][k] * c[places - j + i - 1][i - k] % MOD;
				int k = K - j;
					for (int l = 0; l <= j && l <= i; l++) {
						if (i - l > k)
							continue;
						next[j] += answer[j + i - l] * coef[l];
						if ((l & 3) == 3)
							next[j] %= MOD;
					}
					next[j] %= MOD;
			}
			long[] t = answer;
			answer = next;
			next = t;
		}
		if (places != 1)
			throw new RuntimeException();
		return (int) answer[1];
    }
}
