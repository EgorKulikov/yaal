package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Islands {
	private boolean[][][] processed;
	private int finishRow;
	private int finishColumn;
	private int[][] bridges;
	int[] dx = {1, 0, -1, 0};
	int[] dy = {0, -1, 0, 1};
	private int rowCount;
	private int columnCount;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		rowCount = in.readInt();
		columnCount = in.readInt();
		bridges = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				bridges[i][j] = in.readCharacter() - '0';
		}
		processed = new boolean[2][rowCount][columnCount];
		int startRow = in.readInt() - 1;
		int startColumn = in.readInt() - 1;
		finishRow = in.readInt() - 1;
		finishColumn = in.readInt() - 1;
		if (go(1, startRow, startColumn))
			out.printLine("YES");
		else
			out.printLine("NO");
	}

	private boolean go(int canUse, int row, int column) {
		if (row == finishRow && column == finishColumn)
			return true;
		if (processed[canUse][row][column])
			return false;
		processed[canUse][row][column] = true;
		boolean canJump = bridges[row][column] > 1 || bridges[row][column] == 1 && canUse == 1;
		for (int i = 0; i < 4; i++) {
			int nextRow = row + dx[i];
			int nextColumn = column + dy[i];
			if (nextRow >= 0 && nextRow < rowCount && nextColumn >= 0 && nextColumn < columnCount) {
				if (canJump && go(1, nextRow, nextColumn))
					return true;
				else if (bridges[nextRow][nextColumn] != 0 && go(0, nextRow, nextColumn))
					return true;
			}
		}
		return false;
	}
}
