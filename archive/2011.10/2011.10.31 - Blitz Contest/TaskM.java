package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskM {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(IntegerUtils.gcd(in.readLong(), in.readLong()));
	}
}
