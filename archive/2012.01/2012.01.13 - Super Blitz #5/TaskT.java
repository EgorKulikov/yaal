package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskT {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		out.printLine((1 << first) + (1 << second));
	}
}
