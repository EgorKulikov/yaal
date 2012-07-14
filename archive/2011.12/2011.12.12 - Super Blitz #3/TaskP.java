package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskP {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int row = in.readInt();
		int column = in.readInt();
		for (int i = 0; i < 4; i++) {
			int newRow = row + MiscUtils.DX4[i];
			int newColumn = column + MiscUtils.DY4[i];
			if (newRow > 0 && newRow <= rowCount && newColumn > 0 && newColumn <= columnCount)
				out.printLine(newRow, newColumn);
		}
	}
}
