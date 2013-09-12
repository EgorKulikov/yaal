package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	int[] row = new int[32];
	int[] column = new int[32];

	{
		for (int i = 0; i < 32; i++) {
			row[i] = i / 4;
			column[i] = 2 * (3 - i % 4) + (i / 4 % 2 == 0 ? 0 : 1);
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstCount = in.readInt();
		int secondCount = in.readInt();
		if (firstCount == 0 && secondCount == 0)
			throw new UnknownError();
		int[][] board = new int[8][8];
		for (int i = 0; i < firstCount; i++) {
			int index = in.readInt();
			if (index > 0)
				board[row[index - 1]][column[index - 1]] = 1;
			else
				board[row[-index - 1]][column[-index - 1]] = 2;
		}
		for (int i = 0; i < secondCount; i++) {
			int index = in.readInt();
			if (index > 0)
				board[row[index - 1]][column[index - 1]] = -1;
			else
				board[row[-index - 1]][column[-index - 1]] = -2;
		}
		int moveCount = in.readInt();
		boolean toMove = in.readCharacter() == 'W';
		for (int i = 0; i < moveCount; i++) {
			int[] move = MiscUtils.getIntArray(in.readString().replace('-', ' '));
			MiscUtils.decreaseByOne(move);
			int sign = toMove ? -1 : 1;
			if (board[row[move[0]]][column[move[0]]] * sign <= 0) {
				fail(in, out, moveCount, i);
				return;
			}
			for (int j = 1; j < move.length; j++) {
				if (board[row[move[j]]][column[move[j]]] != 0 && (row[move[j]] != row[move[0]] || column[move[j]] != column[move[0]])) {
					fail(in, out, moveCount, i);
					return;
				}
			}
			if (move.length == 2 && Math.abs(row[move[0]] - row[move[1]]) == 1 && Math.abs(column[move[0]] - column[move[1]]) == 1) {
				if ((row[move[1]] - row[move[0]]) * sign < 0 && board[row[move[0]]][column[move[0]]] * sign != 2) {
					fail(in, out, moveCount, i);
					return;
				}
				for (int j = 0; j < 8; j++) {
					for (int k = 0; k < 8; k++) {
						if (board[j][k] * sign > 0) {
							if (MiscUtils.isValidCell(j + sign * 2, k - 2, 8, 8) && board[j + sign * 2][k - 2] == 0 && board[j + sign][k - 1] * sign < 0) {
								fail(in, out, moveCount, i);
								return;
							}
							if (MiscUtils.isValidCell(j + sign * 2, k + 2, 8, 8) && board[j + sign * 2][k + 2] == 0 && board[j + sign][k + 1] * sign < 0) {
								fail(in, out, moveCount, i);
								return;
							}
							if (Math.abs(board[j][k]) == 2) {
								if (MiscUtils.isValidCell(j - sign * 2, k - 2, 8, 8) && board[j - sign * 2][k - 2] == 0 && board[j - sign][k - 1] * sign < 0) {
									fail(in, out, moveCount, i);
									return;
								}
								if (MiscUtils.isValidCell(j - sign * 2, k + 2, 8, 8) && board[j - sign * 2][k + 2] == 0 && board[j - sign][k + 1] * sign < 0) {
									fail(in, out, moveCount, i);
									return;
								}
							}
						}
					}
				}
				board[row[move[1]]][column[move[1]]] = board[row[move[0]]][column[move[0]]];
				board[row[move[0]]][column[move[0]]] = 0;
				if (row[move[1]] == 7 && sign == 1 || row[move[1]] == 0 && sign == -1)
					board[row[move[1]]][column[move[1]]] = 2 * sign;
			} else {
				boolean isKing = Math.abs(board[row[move[0]]][column[move[0]]]) == 2;
				board[row[move[0]]][column[move[0]]] = 0;
				for (int j = 1; j < move.length; j++) {
					int dRow = row[move[j]] - row[move[j - 1]];
					int dColumn = column[move[j]] - column[move[j - 1]];
					if (Math.abs(dRow) != 2 || Math.abs(dColumn) != 2 || dRow * sign == -2 && !isKing) {
						fail(in, out, moveCount, i);
						return;
					}
					int mRow = row[move[j]] - dRow / 2;
					int mColumn = column[move[j]] - dColumn / 2;
					if (board[mRow][mColumn] * sign >= 0) {
						fail(in, out, moveCount, i);
						return;
					}
					board[mRow][mColumn] = 0;
				}
				int j = row[move[move.length - 1]];
				int k = column[move[move.length - 1]];
				if (MiscUtils.isValidCell(j + sign * 2, k - 2, 8, 8) && board[j + sign * 2][k - 2] == 0 && board[j + sign][k - 1] * sign < 0) {
					fail(in, out, moveCount, i);
					return;
				}
				if (MiscUtils.isValidCell(j + sign * 2, k + 2, 8, 8) && board[j + sign * 2][k + 2] == 0 && board[j + sign][k + 1] * sign < 0) {
					fail(in, out, moveCount, i);
					return;
				}
				if (isKing) {
					if (MiscUtils.isValidCell(j - sign * 2, k - 2, 8, 8) && board[j - sign * 2][k - 2] == 0 && board[j - sign][k - 1] * sign < 0) {
						fail(in, out, moveCount, i);
						return;
					}
					if (MiscUtils.isValidCell(j - sign * 2, k + 2, 8, 8) && board[j - sign * 2][k + 2] == 0 && board[j - sign][k + 1] * sign < 0) {
						fail(in, out, moveCount, i);
						return;
					}
				}
				if (j == 7 && sign == 1 || j == 0 && sign == -1)
					isKing = true;
				board[j][k] = isKing ? 2 * sign : sign;
			}
			toMove = !toMove;
		}
		out.printLine("All moves valid");
    }

	private void fail(InputReader in, OutputWriter out, int moveCount, int i) {
		out.printLine("Move", i + 1, "is invalid");
		skipMoves(moveCount - i - 1, in);
	}

	private void skipMoves(int count, InputReader in) {
		IOUtils.readStringArray(in, count);
	}
}
