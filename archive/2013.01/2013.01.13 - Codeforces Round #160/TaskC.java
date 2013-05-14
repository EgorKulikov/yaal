package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long max = in.readLong() + 1;
		long sum = in.readLong();
		if ((sum & (sum - 1)) != 0) {
			out.printLine(0);
			return;
		}
		int required = Long.bitCount(sum - 1) + 1;
		long[][] c = IntegerUtils.generateBinomialCoefficients(41);
		long result = c[Long.bitCount(Long.highestOneBit(max) - 1)][required];
		required--;
		for (int i = Long.bitCount(Long.highestOneBit(max) - 1) - 1; i >= 0; i--) {
			if ((max >> i & 1) == 1) {
				result += c[i][required];
				required--;
				if (required < 0)
					break;
			}
		}
		if (required == 0)
			result++;
		if (sum == 1)
			result--;
		out.printLine(result);
    }
}
