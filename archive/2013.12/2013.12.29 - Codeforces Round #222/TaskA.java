package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int fill = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int[] rowQueue = new int[rowCount * columnCount];
		int[] columnQueue = new int[rowCount * columnCount];
		int size = 0;
		for (int i = 0; i < rowCount && size == 0; i++) {
			for (int j = 0; j < columnCount && size == 0; j++) {
				if (map[i][j] == '.') {
					rowQueue[0] = i;
					columnQueue[0] = j;
					map[i][j] = 'X';
					size = 1;
				}
			}
		}
		for (int i = 0; i < size; i++) {
			int row = rowQueue[i];
			int column = columnQueue[i];
			for (int j = 0; j < 4; j++) {
				int nRow = row + MiscUtils.DX4[j];
				int nColumn = column + MiscUtils.DY4[j];
				if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) && map[nRow][nColumn] == '.') {
					map[nRow][nColumn] = 'X';
					rowQueue[size] = nRow;
					columnQueue[size++] = nColumn;
				}
			}
		}
		for (int i = 0; i < size - fill; i++)
			map[rowQueue[i]][columnQueue[i]] = '.';
		for (char[] row : map)
			out.printLine(row);
    }
}
