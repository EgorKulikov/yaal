package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		long columnCount = in.readLong();
		int total = in.readInt();
		long[] answer = new long[total + 1];
		answer[0] = 1;
		long[] c = IntegerUtils.generateBinomialCoefficients(rowCount + 1, MOD)[rowCount];
		long[] c2 = new long[rowCount + 1];
		for (int i = 0; i <= rowCount; i++)
			c2[i] = IntegerUtils.power(c[i], columnCount / rowCount, MOD);
		for (int i = 0; i <= rowCount; i++)
			c[i] = IntegerUtils.power(c[i], columnCount / rowCount + 1, MOD);
		int threshold = (int) (columnCount % rowCount);
		for (int i = 0; i < rowCount; i++) {
			long[] multiplier;
			if (i < threshold)
				multiplier = c;
			else
				multiplier = c2;
			for (int j = Math.min(total, (i + 1) * rowCount); j >= 0; j--) {
				for (int k = 1; k <= rowCount && k <= j; k++)
					answer[j] += answer[j - k] * multiplier[k] % MOD;
				answer[j] %= MOD;
			}
		}
		out.printLine(answer[total]);
	}
}
