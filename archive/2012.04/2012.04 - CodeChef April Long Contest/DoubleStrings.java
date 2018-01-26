package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class DoubleStrings {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		out.printLine(length - length % 2);
	}
}
