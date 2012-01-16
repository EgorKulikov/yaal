package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		out.printLine(n & (n - 1));
	}
}
