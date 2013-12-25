package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	static final long MOD = (long) (2e9 + 13);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		if (rowCount > columnCount) {
			int t = rowCount;
			rowCount = columnCount;
			columnCount = t;
		}
		if (rowCount == 1) {
			long[][] c = IntegerUtils.generateBinomialCoefficients(columnCount + 1, MOD);
			long answer = 0;
			for (int i = 1; i <= columnCount; i++)
				answer += c[columnCount][i] * c[columnCount][i] % MOD;
			answer %= MOD;
			out.printLine(answer);
		} else {
			int total = rowCount * columnCount;
			long[][] c = IntegerUtils.generateBinomialCoefficients(total + 1, MOD);
			long[] factorial = IntegerUtils.generateFactorial(total + 1, MOD);
			long answer = 1;
			for (int i = 1; i < total - 1; i++)
				answer += c[total][i] * c[total][i] % MOD * factorial[i] % MOD;
			int i = total - 1;
			answer += c[total][i] * c[total][i] % MOD * factorial[i] % MOD * ((MOD + 1) / 2) % MOD;
			answer %= MOD;
			out.printLine(answer);
		}
    }
}
