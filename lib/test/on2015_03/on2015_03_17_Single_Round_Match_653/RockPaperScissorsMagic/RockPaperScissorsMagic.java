package on2015_03.on2015_03_17_Single_Round_Match_653.RockPaperScissorsMagic;



import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class RockPaperScissorsMagic {
	private static final long MOD = (long) (1e9 + 7);

	public int count(int win, int lose, int tie, int[] card) {
		if (win == tie && win == lose) {
			return (int) IntegerUtils.power(3, card.length, MOD);
		}
		long[][] c = IntegerUtils.generateBinomialCoefficients(card.length + 1, MOD);
		int[] qty = new int[3];
		for (int i : card) {
			qty[i]++;
		}
		Arrays.sort(qty);
		if ((qty[1] - qty[0]) % 3 != 0 || (qty[2] - qty[0]) % 3 != 0) {
			return 0;
		}
		int d1 = (qty[1] - qty[0]) / 3;
		int d2 = (qty[2] - qty[0]) / 3;
		long answer = 0;
		for (int i = 0; i <= qty[0]; i++) {
			for (int j = 0; i + j <= qty[0]; j++) {
				long current = c[qty[0]][i] * c[qty[0] - i][j] % MOD *
					c[qty[1]][i + d1] % MOD * c[qty[1] - i - d1][j + d1] % MOD *
					c[qty[2]][i + d2] % MOD * c[qty[2] - i - d2][j + d2] % MOD;
				answer += current;
			}
		}
		return (int) (answer % MOD);
    }
}
