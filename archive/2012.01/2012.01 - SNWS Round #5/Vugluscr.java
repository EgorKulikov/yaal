package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Vugluscr {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] board = IOUtils.readTable(in, rowCount, columnCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (board[i][j] == '.')
					board[i][j] = '0';
			}
		}
		int[][] up = new int[rowCount][columnCount];
		int[][] down = new int[rowCount][columnCount];
		ArrayUtils.fill(up, Integer.MIN_VALUE);
		ArrayUtils.fill(down, Integer.MIN_VALUE);
		up[rowCount - 1][0] = board[rowCount - 1][0] - '0';
		for (int i = rowCount - 2; i >= 0; i--) {
			if (board[i][0] == '*')
				break;
			up[i][0] = up[i + 1][0] + board[i][0] - '0';
		}
		for (int i = 1; i < columnCount; i++) {
			if (board[rowCount - 1][i] != '*')
				up[rowCount - 1][i] = Math.max(up[rowCount - 1][i - 1], down[rowCount - 1][i - 1]) + board[rowCount - 1][i] - '0';
			for (int j = rowCount - 2; j >= 0; j--) {
				if (board[j][i] != '*')
					up[j][i] = Math.max(Math.max(up[j][i - 1], down[j][i - 1]), up[j + 1][i]) + board[j][i] - '0';
			}
			if (board[0][i] != '*')
				down[0][i] = Math.max(down[0][i - 1], up[0][i - 1]) + board[0][i] - '0';
			for (int j = 1; j < rowCount; j++) {
				if (board[j][i] != '*')
					down[j][i] = Math.max(Math.max(up[j][i - 1], down[j][i - 1]), down[j - 1][i]) + board[j][i] - '0';
			}
		}
		out.printLine(Math.max(up[rowCount - 1][columnCount - 1], down[rowCount - 1][columnCount - 1]));
	}
}
