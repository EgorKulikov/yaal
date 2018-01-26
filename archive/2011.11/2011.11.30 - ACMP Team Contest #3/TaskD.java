package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long key = in.readLong();
		long answer = key + Long.bitCount(key);
		out.printLine(answer);
	}
}
