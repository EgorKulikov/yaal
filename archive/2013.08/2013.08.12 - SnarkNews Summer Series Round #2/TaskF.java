package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int row = in.readInt() - 1;
		int column = in.readInt() - 1;
		if (rowCount > 1 && columnCount > 1) {
			if (rowCount % 2 == 0 || columnCount % 2 == 0)
				out.printLine(rowCount * columnCount);
			else
				out.printLine(rowCount * columnCount + 1);
		} else {
			out.printLine(rowCount * 2 + columnCount * 2 - 4);
		}
    }
}
