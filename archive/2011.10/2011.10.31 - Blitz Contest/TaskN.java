package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskN {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(IntegerUtils.lcm(in.readLong(), in.readLong()));
	}
}
