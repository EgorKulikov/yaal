package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AStonesGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (testNumber == 1)
			in.readInt();
		if (in.isExhausted())
			throw new UnknownError();
		long count = in.readLong();
		if (count % 2 == 1) {
			out.printLine(1);
			return;
		}
		int max = Long.bitCount(Long.highestOneBit(count) - 1) + 1;
		int required = max ^ 1;
		long answer = Integer.MAX_VALUE;
		for (int i = 1; i <= max; i++) {
			if ((i ^ required) < i) {
				int target = i ^ required;
				long difference = Math.max((1L << (i - 1)) - (1L << target) + 1, ((1L << (i - 1)) + 1) / 2);
				answer = Math.min(answer, difference);
			}
		}
		out.printLine(answer);
    }
}
