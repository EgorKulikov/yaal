package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] map = IOUtils.readIntTable(in, rowCount, columnCount);
		int[][] sunOnly = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				sunOnly[i][j] = 1;
				for (int k = -2; k <= 2; k++) {
					for (int l = -2; l <= 2; l++) {
						if (MiscUtils.isValidCell(i + k, j + l, rowCount, columnCount))
							sunOnly[i][j] &= map[i + k][j + l];
						else
							sunOnly[i][j] = 0;
					}
				}
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				map[i][j] -= 2;
			}
		}
		int[] rowQueue = new int[rowCount * columnCount];
		int[] columnQueue = new int[rowCount * columnCount];
		int count = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == -1) {
					int size = 1;
					rowQueue[0] = i;
					columnQueue[0] = j;
					map[i][j] = count;
					for (int k = 0; k < size; k++) {
						int row = rowQueue[k];
						int column = columnQueue[k];
						for (int l = 0; l < 4; l++) {
							int nRow = row + MiscUtils.DX4[l];
							int nColumn = column + MiscUtils.DY4[l];
							if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) && map[nRow][nColumn] == -1) {
								map[nRow][nColumn] = count;
								rowQueue[size] = nRow;
								columnQueue[size++] = nColumn;
							}
						}
					}
					count++;
				}
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == -2)
					continue;
				for (int k = -4; k <= 4; k++) {
					for (int l = -4; l <= 4; l++) {
						if (MiscUtils.isValidCell(i + k, j + l, rowCount, columnCount) && sunOnly[i + k][j + l] == 1)
							map[i][j] = -2;
					}
				}
			}
		}
		int[] answer = new int[count];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] >= 0) {
					answer[map[i][j]]++;
					int size = 1;
					rowQueue[0] = i;
					columnQueue[0] = j;
					map[i][j] = -2;
					for (int k = 0; k < size; k++) {
						int row = rowQueue[k];
						int column = columnQueue[k];
						for (int l = 0; l < 4; l++) {
							int nRow = row + MiscUtils.DX4[l];
							int nColumn = column + MiscUtils.DY4[l];
							if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) && map[nRow][nColumn] >= 0) {
								map[nRow][nColumn] = -2;
								rowQueue[size] = nRow;
								columnQueue[size++] = nColumn;
							}
						}
					}
				}
			}
		}
		Arrays.sort(answer);
		out.printLine(count);
		out.printLine(answer);
    }
}
