package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int row = in.readInt();
		int column = in.readInt();
		if (row == column) {
			if (row == 1)
				out.printLine(0);
			else
				out.printLine(2);
		} else
			out.printLine(1);
	}
}
