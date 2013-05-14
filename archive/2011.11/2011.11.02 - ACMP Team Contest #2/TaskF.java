package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long square = in.readLong() * in.readLong();
		long value = (long) (Math.sqrt(square) - 1);
		for (int i = 0; i < 4; i++) {
			if (value * value == square) {
				out.printLine(value);
				return;
			}
			value++;
		}
		out.printLine(0);
	}
}
