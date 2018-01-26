package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		in.readString();
		String type = in.readString();
		if ("week".equals(type)) {
			if (x == 5 || x == 6) {
				out.printLine(53);
			} else {
				out.printLine(52);
			}
		} else {
			if (x <= 29) {
				out.printLine(12);
			} else if (x == 30) {
				out.printLine(11);
			} else {
				out.printLine(7);
			}
		}
	}
}
