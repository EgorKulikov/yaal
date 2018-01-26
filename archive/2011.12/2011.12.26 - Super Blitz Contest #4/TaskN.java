package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskN {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt();
		out.printLine(IntegerUtils.generateFibonacci(index + 2, -1)[index + 1]);
	}
}
