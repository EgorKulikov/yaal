package on2012_12.on2012_12_22_Codeforces_Round__157.B___Little_Elephant_and_Elections;



import net.egork.numbers.IntegerUtils;
import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int limit = in.readInt();
		final long[] perCount = new long[10];
		final long[][] c = IntegerUtils.generateBinomialCoefficients(11, MOD);
		final long[] pow2 = IntegerUtils.generatePowers(2, 11, MOD);
		final long[] pow8 = IntegerUtils.generatePowers(8, 11, MOD);
		NumberIterator iterator = new NumberIterator() {
			@Override
			protected void process(long prefix, int remainingDigits) {
				int should = 0;
				while (prefix != 0) {
					int digit = (int) (prefix % 10);
					if (digit == 4 || digit == 7)
						should++;
					prefix /= 10;
				}
				for (int i = 0; i <= remainingDigits; i++)
					perCount[i + should] += c[remainingDigits][i] * pow2[i] * pow8[remainingDigits - i];
			}
		};
		iterator.run(1, limit);
		long answer = 0;
		for (int i = 1; i <= 9; i++) {
			if (perCount[i] == 0)
				continue;
			long[][][] value = new long[i + 1][7][i];
			value[0][0][0] = 1;
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < 7; k++) {
					for (int m = 0; m < i; m++) {
						long multiplier = 1;
						for (int l = 0; k + l < 7 && l <= perCount[j] && m + j * l < i; l++) {
							value[j + 1][k + l][m + j * l] += value[j][k][m] * multiplier % MOD * c[k + l][l] % MOD;
							value[j + 1][k + l][m + j * l] %= MOD;
							multiplier *= perCount[j] - l;
							multiplier %= MOD;
						}
					}
				}
			}
			for (int j = 0; j < i; j++)
				answer += value[i][6][j] * perCount[i] % MOD;
		}
		answer %= MOD;
		out.printLine(answer);
	}
}
