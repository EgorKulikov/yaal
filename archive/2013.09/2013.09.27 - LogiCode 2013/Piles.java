package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Piles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long first = in.readLong();
		long second = in.readLong();
		if (first % 2 == 1 && second % 2 == 1)
			out.printLine("B");
		else
			out.printLine("A");
    }
}
