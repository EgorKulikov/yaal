package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ProjectSpoon {
	static long[][] c = IntegerUtils.generateBinomialCoefficients(100);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		for (int i = 1; ; i++) {
			if (c[i][i >> 1] >= count) {
				out.printLine(i);
				return;
			}
		}
    }
}
