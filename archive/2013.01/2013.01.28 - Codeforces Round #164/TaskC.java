package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		out.printLine(Math.min(rowCount, columnCount) + 1);
		for (int i = 0; i <= Math.min(rowCount, columnCount); i++)
			out.printLine(i, Math.min(rowCount, columnCount) - i);
    }
}
