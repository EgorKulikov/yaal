package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int queryCount = in.readInt();
		char[][] rectangle = IOUtils.readTable(in, rowCount, columnCount);
		int[][][][] answer = new int[rowCount][columnCount][rowCount][columnCount];
		int[][] up = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (rectangle[i][j] == '0') {
					if (i == 0)
						up[i][j] = 1;
					else
						up[i][j] = up[i - 1][j] + 1;
				}
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				for (int k = i; k < rowCount; k++) {
					for (int l = j; l < columnCount; l++) {
						if (k != i)
							answer[i][j][k][l] += answer[i][j][k - 1][l];
						if (l != j)
							answer[i][j][k][l] += answer[i][j][k][l - 1];
						if (k != i && l != j)
							answer[i][j][k][l] -= answer[i][j][k - 1][l - 1];
						int limit = k - i + 1;
						for (int m = l; m >= j && limit > 0; m--) {
							limit = Math.min(limit, up[k][m]);
							answer[i][j][k][l] += limit;
						}
					}
				}
			}
		}
		for (int i = 0; i < queryCount; i++) {
			int fromRow = in.readInt() - 1;
			int fromColumn = in.readInt() - 1;
			int toRow = in.readInt() - 1;
			int toColumn = in.readInt() - 1;
			out.printLine(answer[fromRow][fromColumn][toRow][toColumn]);
		}
    }
}
