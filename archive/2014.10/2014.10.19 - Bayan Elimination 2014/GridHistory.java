package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GridHistory {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine("Case #" + testNumber + ":");
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[] number = IOUtils.readIntArray(in, rowCount * columnCount);
		int[] order = ArrayUtils.order(number);
		int row = -1;
		int column = -1;
		int last = -1;
		boolean[][] map = new boolean[rowCount][columnCount];
		ArrayUtils.fill(map, true);
		int[][] distance = new int[rowCount][columnCount];
		int[] rowQueue = new int[rowCount * columnCount];
		int[] columnQueue = new int[rowCount * columnCount];
		boolean good = false;
		for (int i : order) {
			int cRow = i / columnCount;
			int cColumn = i % columnCount;
			int size = 1;
			rowQueue[0] = cRow;
			columnQueue[0] = cColumn;
			ArrayUtils.fill(distance, Integer.MAX_VALUE / 2);
			distance[cRow][cColumn] = 0;
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < 4; k++) {
					int nRow = rowQueue[j] + MiscUtils.DX4[k];
					int nColumn = columnQueue[j] + MiscUtils.DY4[k];
					if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) && map[nRow][nColumn] && distance[nRow][nColumn] == Integer.MAX_VALUE / 2) {
						distance[nRow][nColumn] = distance[rowQueue[j]][columnQueue[j]] + 1;
						rowQueue[size] = nRow;
						columnQueue[size++] = nColumn;
					}
				}
			}
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (map[j][k] && distance[j][k] == Integer.MAX_VALUE / 2) {
						out.printLine("NO");
						return;
					}
				}
			}
			if (row != -1) {
				if ((row + column + cRow + cColumn + number[i] + last) % 2 != 0) {
					out.printLine("NO");
					return;
				}
				if (distance[row][column] > number[i] - last) {
					out.printLine("NO");
					return;
				}
				map[row][column] = false;
				if (number[i] - last == 1) {
					good = true;
				} else {
					good = false;
				}
			}
			row = cRow;
			column = cColumn;
			last = number[i];
		}
		out.printLine(good ? "YES" : "NO");
    }
}
