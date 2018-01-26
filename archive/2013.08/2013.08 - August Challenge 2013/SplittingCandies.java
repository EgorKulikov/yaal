package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class SplittingCandies {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long total = in.readLong();
		long count = in.readLong();
		if (count != 0)
			out.printLine(total / count, total % count);
		else
			out.printLine(0, total);
    }
}
