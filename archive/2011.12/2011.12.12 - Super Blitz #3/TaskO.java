package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskO {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] table = IOUtils.readIntTable(in, rowCount, columnCount);
		int[][] answer = new int[columnCount][rowCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				answer[j][rowCount - i - 1] = table[i][j];
		}
		out.printLine(columnCount, rowCount);
		for (int[] row : answer)
			out.printLine(Array.wrap(row).toArray());
	}
}
