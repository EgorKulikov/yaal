package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		int[][] minShift = new int[rowCount][columnCount];
		ArrayUtils.fill(minShift, Integer.MAX_VALUE / 2);
		for (int i = 0; i < rowCount; i++) {
			int current = Integer.MAX_VALUE / 2;
			for (int j = 0; j < columnCount; j++) {
				current++;
				if (table[i][j] == '1')
					current = 0;
				minShift[i][j] = Math.min(minShift[i][j], current);
			}
			if (current >= Integer.MAX_VALUE / 2) {
				out.printLine(-1);
				return;
			}
			for (int j = 0; j < columnCount; j++) {
				current++;
				if (table[i][j] == '1')
					current = 0;
				minShift[i][j] = Math.min(minShift[i][j], current);
			}
			current = Integer.MAX_VALUE / 2;
			for (int j = columnCount - 1; j >= 0; j--) {
				current++;
				if (table[i][j] == '1')
					current = 0;
				minShift[i][j] = Math.min(minShift[i][j], current);
			}
			for (int j = columnCount - 1; j >= 0; j--) {
				current++;
				if (table[i][j] == '1')
					current = 0;
				minShift[i][j] = Math.min(minShift[i][j], current);
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < columnCount; i++) {
			int current = 0;
			for (int j = 0; j < rowCount; j++)
				current += minShift[j][i];
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
	}
}
