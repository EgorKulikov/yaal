package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskM {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		out.printLine(swaps(count / 2) + swaps((count + 1) / 2));
	}

	private int swaps(int length) {
		return length * (length - 1) / 2;
	}
}
