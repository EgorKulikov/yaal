package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Xtra {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long sum = 0;
		while (true) {
			int number = in.readInt();
			if (number == 0) {
				return;
			}
			out.printLine(sum += number);
			out.flush();
		}
	}
}
