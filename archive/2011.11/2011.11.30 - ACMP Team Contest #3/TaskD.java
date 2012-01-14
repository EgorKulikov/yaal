package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long key = in.readLong();
		long answer = key + Long.bitCount(key);
		out.printLine(answer);
	}
}
