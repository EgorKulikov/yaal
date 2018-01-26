package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int cageCount = in.readInt();
		int hareCount = in.readInt();
		out.printLine((hareCount + cageCount - 1) / cageCount);
	}
}
