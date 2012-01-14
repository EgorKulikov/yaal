package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Binary {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int bound = in.readInt();
		int lower = bound - 2 * (bound - Integer.highestOneBit(bound));
		int lowerLength = Integer.bitCount(Integer.highestOneBit(bound) - 1);
		for (int i = 0; i < lower; i++) {
			for (int j = lowerLength - 1; j >= 0; j--)
				out.print(i >> j & 1);
			out.printLine();
		}
		int to = Integer.highestOneBit(bound);
		for (int i = lower; i < to; i++) {
			for (int j = lowerLength - 1; j >= 0; j--)
				out.print(i >> j & 1);
			out.printLine(0);
			for (int j = lowerLength - 1; j >= 0; j--)
				out.print(i >> j & 1);
			out.printLine(1);
		}
	}
}
