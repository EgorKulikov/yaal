package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int mines = in.readInt();
		out.printLine("Case #" + testNumber + ":");
		char[][] answer = new char[rowCount][columnCount];
		int notMines = 0;
		if (mines + 1 == rowCount * columnCount) {
			ArrayUtils.fill(answer, '*');
			answer[0][0] = 'c';
		} else if (rowCount == 1 || columnCount == 1) {
			notMines = 1;
			answer[0][0] = 'c';
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					if (answer[i][j] == 0) {
						if (notMines + mines == rowCount * columnCount)
							answer[i][j] = '*';
						else {
							answer[i][j] = '.';
							notMines++;
						}
					}
				}
			}
		} else if (rowCount == 2 || columnCount == 2) {
			if (mines % 2 != 0 || rowCount * columnCount - mines == 2)
				answer = null;
			else {
				if (rowCount == 2) {
					for (int i = 0; i < columnCount - mines / 2; i++) {
						answer[0][i] = answer[1][i] = '.';
					}
					for (int i = columnCount - mines / 2; i < columnCount; i++) {
						answer[0][i] = answer[1][i] = '*';
					}
					answer[0][0] = 'c';
				} else {
					for (int i = 0; i < rowCount - mines / 2; i++) {
						answer[i][0] = answer[i][1] = '.';
					}
					for (int i = rowCount - mines / 2; i < rowCount; i++) {
						answer[i][0] = answer[i][1] = '*';
					}
					answer[0][0] = 'c';
				}
			}
		} else {
			int requiredNotMines = rowCount * columnCount - mines;
			if (requiredNotMines == 3 || requiredNotMines == 5 || requiredNotMines == 7 || requiredNotMines == 2)
				answer = null;
			else {
				if (requiredNotMines % 2 == 1) {
					answer[2][0] = answer[2][1] = answer[2][2] = '.';
					notMines += 3;
				}
				for (int i = 0; i < columnCount; i++) {
					if (notMines + mines == rowCount * columnCount)
						answer[0][i] = answer[1][i] = '*';
					else {
						answer[0][i] = answer[1][i] = '.';
						notMines += 2;
					}
				}
				for (int i = 2; i < rowCount; i++) {
					if (answer[i][0] == 0) {
						if (notMines + mines == rowCount * columnCount)
							answer[i][0] = answer[i][1] = '*';
						else {
							answer[i][0] = answer[i][1] = '.';
							notMines += 2;
						}
					}
				}
				for (int i = 2; i < rowCount; i++) {
					for (int j = 2; j < columnCount; j++) {
						if (answer[i][j] == 0) {
							if (notMines + mines == rowCount * columnCount)
								answer[i][j] = '*';
							else {
								answer[i][j] = '.';
								notMines++;
							}
						}
					}
				}
				answer[0][0] = 'c';
			}
		}
		if (answer == null)
			out.printLine("Impossible");
		else {
			for (char[] row : answer)
				out.printLine(row);
		}
    }
}
