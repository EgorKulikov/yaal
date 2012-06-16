package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] labyrinth = IOUtils.readTable(in, rowCount, columnCount);
		boolean[][] visited = new boolean[rowCount][columnCount];
		int[][] visitedRow = new int[rowCount][columnCount];
		int[][] visitedColumn = new int[rowCount][columnCount];
		int[] rowQueue = new int[rowCount * columnCount];
		int[] columnQueue = new int[rowCount * columnCount];
		int size = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (labyrinth[i][j] == 'S') {
					visited[i][j] = true;
					visitedRow[i][j] = i;
					visitedColumn[i][j] = j;
					rowQueue[size] = i;
					columnQueue[size++] = j;
				}
			}
		}
		for (int i = 0; i < size; i++) {
			int row = rowQueue[i];
			int column = columnQueue[i];
			for (int j = 0; j < 4; j++) {
				int newRow = row + MiscUtils.DX4[j];
				int newColumn = column + MiscUtils.DY4[j];
				if (newRow == rowCount)
					newRow = 0;
				else if (newRow == -1)
					newRow = rowCount - 1;
				if (newColumn == columnCount)
					newColumn = 0;
				else if (newColumn == -1)
					newColumn = columnCount - 1;
				if (labyrinth[newRow][newColumn] != '#') {
					int actualRow = visitedRow[row][column] + MiscUtils.DX4[j];
					int actualColumn = visitedColumn[row][column] + MiscUtils.DY4[j];
					if (visited[newRow][newColumn]) {
						if (actualRow != visitedRow[newRow][newColumn] || actualColumn != visitedColumn[newRow][newColumn]) {
							out.printLine("Yes");
							return;
						}
					} else {
						visited[newRow][newColumn] = true;
						visitedRow[newRow][newColumn] = actualRow;
						visitedColumn[newRow][newColumn] = actualColumn;
						rowQueue[size] = newRow;
						columnQueue[size++] = newColumn;
					}
				}
			}
		}
		out.printLine("No");
	}
}
