package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class TaskB {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] configRows = {4, 4, 3, 5, 4, 4};
		int[] configColumns = {4, 4, 5, 3, 4, 4};
		int[][] deltaRows = {{0, 2, 3, 1}, {0, 1, 3, 2}, {0, 1, 2, 1}, {0, 2, 4, 2}, {0, 1, 3, 2}, {0, 2, 3, 1}};
		int[][] deltaColumns = {{2, 3, 1, 0}, {1, 3, 2, 0}, {2, 4, 2, 0}, {1, 2, 1, 0}, {0, 2, 3, 1}, {3, 2, 0, 1}};
		long rowCount = in.readInt();
		long columnCount = in.readInt();
		int stoneCount = in.readInt();
		int[] rows = new int[stoneCount];
		int[] columns = new int[stoneCount];
		IOUtils.readIntArrays(in, rows, columns);
		Set<Long> stones = new HashSet<Long>();
		for (int i = 0; i < stoneCount; i++)
			stones.add(rows[i] * columnCount + columns[i]);
		long answer = 0;
		for (int i = 0; i < configRows.length; i++)
			answer += (rowCount - configRows[i] + 1) * (columnCount - configColumns[i] + 1);
		for (int i = 0; i < stoneCount; i++) {
			for (int j = 0; j < configRows.length; j++) {
				for (int k = 0; k < 4; k++) {
					int centerRow = rows[i] - deltaRows[j][k];
					int centerColumn = columns[i] - deltaColumns[j][k];
					if (centerRow <= 0 || centerRow + configRows[j] - 1 > rowCount ||
						centerColumn <= 0 || centerColumn + configColumns[j] - 1 > columnCount)
					{
						continue;
					}
					boolean good = true;
					long key = centerRow * columnCount + centerColumn;
					for (int l = k + 1; l < 4 && good; l++) {
						if (stones.contains(key + deltaRows[j][l] * columnCount + deltaColumns[j][l]))
							good = false;
					}
					if (good)
						answer--;
				}
			}
		}
		answer *= 8;
		out.println("Case " + testNumber + ": " + answer);
	}
}
