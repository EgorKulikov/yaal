package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		if (Long.bitCount(n) == 1)
			out.printLine("TAK");
		else
			out.printLine("NIE");
	}
}
