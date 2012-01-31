package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] table = IOUtils.readIntTable(in, rowCount, columnCount);
		int[] row = new int[rowCount];
		Arrays.fill(row, Integer.MAX_VALUE);
		int[] column = new int[columnCount];
		Arrays.fill(column, Integer.MIN_VALUE);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				row[i] = Math.min(row[i], table[i][j]);
				column[j] = Math.max(column[j], table[i][j]);
			}
		}
		int answer = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] == row[i] && table[i][j] == column[j])
					answer++;
			}
		}
		out.printLine(answer);
	}
}
