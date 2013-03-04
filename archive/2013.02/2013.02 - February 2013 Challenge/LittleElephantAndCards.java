package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleElephantAndCards {
	static final long MOD = (long) (1e9 + 7);
	long[][] c = IntegerUtils.generateBinomialCoefficients(1001, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		IOUtils.readIntArray(in, count);
		if ((count & 1) == 1)
			out.printLine(IntegerUtils.power(2, count - 1, MOD));
		else {
			long answer = IntegerUtils.power(2, count - 1, MOD) - c[count - 1][count >> 1];
			if (answer < 0)
				answer += MOD;
			out.printLine(answer);
		}
	}
}
