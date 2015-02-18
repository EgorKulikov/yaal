package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private char[][] map;
	private int buben;
	private int columnCount;
	private int[][] upRow;
	private int[][] upColumn;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		columnCount = in.readInt();
		int queryCount = in.readInt();
		buben = (int) Math.sqrt(rowCount);
//		buben = 4;
		map = IOUtils.readTable(in, rowCount, columnCount);
		upRow = new int[rowCount][columnCount];
		upColumn = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			updateRow(i);
		}
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			int row = in.readInt() - 1;
			int column = in.readInt() - 1;
			if (type == 'C') {
				char to = in.readCharacter();
				int upTo = row + buben - row % buben;
				upTo = Math.min(upTo, rowCount);
				map[row][column] = to;
				for (int j = row; j < upTo; j++) {
					updateRow(j);
				}
			} else {
				while (row >= 0 && column >= 0 && column < columnCount) {
					int nRow = upRow[row][column];
					int nColumn = upColumn[row][column];
					row = nRow;
					column = nColumn;
				}
				out.printLine(row + 1, column + 1);
			}
		}
    }

	private void updateRow(int i) {
		boolean terminalRow = i % buben == 0;
		for (int j = 0; j < columnCount; j++) {
			if (map[i][j] == '^') {
				if (terminalRow) {
					upRow[i][j] = i - 1;
					upColumn[i][j] = j;
				} else {
					upRow[i][j] = upRow[i - 1][j];
					upColumn[i][j] = upColumn[i - 1][j];
				}
			}
			if (j < columnCount - 1 && map[i][j] == '>' && map[i][j + 1] == '<') {
				upRow[i][j] = -2;
				upColumn[i][j] = -2;
				upRow[i][j + 1] = -2;
				upColumn[i][j + 1] = -2;
			}
		}
		for (int j = 0; j < columnCount; j++) {
			if (map[i][j] == '<' && (j == 0 || map[i][j - 1] != '>')) {
				upRow[i][j] = j > 0 ? upRow[i][j - 1] : i;
				upColumn[i][j] = j > 0 ? upColumn[i][j - 1] : -1;
			}
		}
		for (int j = columnCount - 1; j >= 0; j--) {
			if (map[i][j] == '>' && (j == columnCount - 1 || map[i][j + 1] != '<')) {
				upRow[i][j] = j < columnCount - 1 ? upRow[i][j + 1] : i;
				upColumn[i][j] = j < columnCount - 1 ? upColumn[i][j + 1] : columnCount;
			}
		}
	}
}
