package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int s = a + b;
		if (s <= 0) {
			out.printLine(-1);
		} else {
			out.printLine(Math.sqrt(s));
		}
	}
}
