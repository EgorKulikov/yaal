package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleElephantAndMouses {
	int rowCount, columnCount;
	char[][] board;
	int[][][] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		rowCount = in.readInt();
		columnCount = in.readInt();
		board = IOUtils.readTable(in, rowCount, columnCount);
		answer = new int[4][rowCount][columnCount];
		ArrayUtils.fill(answer, -1);
		out.printLine(go(0, 0, 0) + board[0][0] - '0');
    }

	private int go(int fromLeft, int row, int column) {
		if (answer[fromLeft][row][column] != -1)
			return answer[fromLeft][row][column];
		int delta = 0;
		if (row > 0 && fromLeft == 3 && board[row - 1][column] == '1')
			delta++;
		if (column < columnCount - 1 && board[row][column + 1] == '1')
			delta++;
		if (column > 0 && fromLeft == 0 && board[row][column - 1] == '1')
			delta++;
		if (row < rowCount - 1 && board[row + 1][column] == '1')
			delta++;
		if (row == rowCount - 1 && column == columnCount - 1)
			return answer[fromLeft][row][column] = delta;
		int result = Integer.MAX_VALUE;
		if (row != rowCount - 1)
			result = Math.min(result, go((fromLeft & 1) << 1, row + 1, column));
		if (column != columnCount - 1)
			result = Math.min(result, go(((fromLeft & 1) << 1) + 1, row, column + 1));
		return answer[fromLeft][row][column] = result + delta;
	}
}
