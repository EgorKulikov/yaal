package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long number = in.readLong();
		if (number == Long.highestOneBit(number))
			out.printLine("I=" + Long.bitCount(number - 1));
		else
			out.printLine(Long.bitCount(Long.highestOneBit(number) - 1) + "<I<" + (Long.bitCount(Long.highestOneBit(number) - 1) + 1));
	}
}
