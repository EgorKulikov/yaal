package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		if (a == b) {
			out.printLine(4);
			return;
		}
		long value = (Math.min(a, b) - 1) / Math.abs(a - b) + 2;
		out.printLine(value * value * 2);
	}
}
