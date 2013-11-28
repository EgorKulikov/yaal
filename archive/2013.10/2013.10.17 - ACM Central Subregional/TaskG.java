package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	char[][] board;
	int[][][] result = new int[8][8][1 << 16];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		board = IOUtils.readTable(in, 8, 8);
		int answer = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != 'W')
					continue;
				board[i][j] = '.';
				ArrayUtils.fill(result, -1);
				answer = Math.max(answer, go(i, j, 0));
				board[i][j] = 'W';
			}
		}
		out.printLine(answer);
    }

	private int go(int row, int column, int mask) {
		if (result[row][column][mask] != -1)
			return result[row][column][mask];
		result[row][column][mask] = 0;
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				int dRow = row + 2 * i;
				int dColumn = column + 2 * j;
				if (!MiscUtils.isValidCell(dRow, dColumn, 8, 8) || board[dRow][dColumn] != '.')
					continue;
				int bRow = row + i;
				int bColumn = column + j;
				int index = ((bRow >> 1) << 2) + (bColumn >> 1);
				if (board[bRow][bColumn] == 'B' && ((mask >> index & 1) == 0))
					result[row][column][mask] = Math.max(result[row][column][mask], 1 + go(dRow, dColumn, mask + (1 << index)));
			}
		}
		return result[row][column][mask];
	}
}
