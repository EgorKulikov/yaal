package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class EvilHacker {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		if (rowCount == 0 || columnCount == 0) {
			out.printLine(0);
			return;
		}
		out.printLine(rowCount / 2 + columnCount / 2 + 1);
	}
}
