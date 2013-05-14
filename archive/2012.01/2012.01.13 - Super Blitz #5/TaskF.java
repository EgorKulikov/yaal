package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int cageCount = in.readInt();
		int hareCount = in.readInt();
		out.printLine((hareCount + cageCount - 1) / cageCount);
	}
}
