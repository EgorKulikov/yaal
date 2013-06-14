package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RowAndColumnOperations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] row = new int[count];
		int[] column = new int[count];
		int maxRow = 0;
		int maxColumn = 0;
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			for (int j = 0; j < 5; j++)
				in.readCharacter();
			int index = in.readInt() - 1;
			int delta = in.readInt();
			if (type == 'R') {
				row[index] += delta;
				maxRow = Math.max(maxRow, row[index]);
			} else {
				column[index] += delta;
				maxColumn = Math.max(maxColumn, column[index]);
			}
		}
		out.printLine(maxRow + maxColumn);
    }
}
