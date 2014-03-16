package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int side = in.readInt();
		int shiftColumn = in.readInt();
		int shiftRow = in.readInt();
		int columnCount = in.readInt();
		int rowCount = in.readInt();
		char[][] answer = new char[rowCount][columnCount];
		ArrayUtils.fill(answer, '.');
		shiftColumn %= 4 * side;
		shiftRow %= 2 * side;
		for (int i = -2 * side; i < shiftRow + rowCount + 2 * side; i += 2 * side) {
			for (int j = -4 * side; j < shiftColumn + columnCount + 4 * side; j += 4 * side) {
				for (int k = 0; k < side; k++) {
					int row = i - shiftRow;
					int column = j + k - shiftColumn;
					if (MiscUtils.isValidCell(row, column, rowCount, columnCount))
						answer[row][column] = '_';
					row = i - shiftRow - k;
					column = j + k + side - shiftColumn;
					if (MiscUtils.isValidCell(row, column, rowCount, columnCount))
						answer[row][column] = '/';
					row = i - shiftRow + k + 1;
					column = j + k + side - shiftColumn;
					if (MiscUtils.isValidCell(row, column, rowCount, columnCount))
						answer[row][column] = '\\';
				}
			}
		}
		for (int i = -3 * side; i < shiftRow + rowCount + 2 * side; i += 2 * side) {
			for (int j = -2 * side; j < shiftColumn + columnCount + 4 * side; j += 4 * side) {
				for (int k = 0; k < side; k++) {
					int row = i - shiftRow;
					int column = j + k - shiftColumn;
					if (MiscUtils.isValidCell(row, column, rowCount, columnCount))
						answer[row][column] = '_';
					row = i - shiftRow - k;
					column = j + k + side - shiftColumn;
					if (MiscUtils.isValidCell(row, column, rowCount, columnCount))
						answer[row][column] = '/';
					row = i - shiftRow + k + 1;
					column = j + k + side - shiftColumn;
					if (MiscUtils.isValidCell(row, column, rowCount, columnCount))
						answer[row][column] = '\\';
				}
			}
		}
		for (char[] row : answer)
			out.printLine(row);
    }
}
