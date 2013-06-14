package on2013_06.on2013_06_03_20_20_Hack___May_Challenge.NPuzzle;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NPuzzle {
	int[][] board;
	int[][] target;
	List<String> answer = new ArrayList<String>();
	boolean[][] fixed;
	int row;
	int column;
	int[] queueRow;
	int[] queueColumn;
	int[][] direction;
	boolean[][] visited;
	String[] directions = {"UP", "RIGHT", "DOWN", "LEFT"};
	Random random = new Random(239);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		fixed = new boolean[size][size];
		board = IOUtils.readIntTable(in, size, size);
		target = new int[size][size];
		visited = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				target[i][j] = i * size + j;
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == 0) {
					row = i;
					column = j;
				}
			}
		}
		direction = new int[size][size];
		queueRow = new int[size * size];
		queueColumn = new int[size * size];
		if (go(size, size)) {
			out.printLine(answer.size());
			for (String s : answer)
				out.printLine(s);
		} else
			out.printLine(0);
    }

	private boolean go(int rowCount, int columnCount) {
		if (rowCount == 3 && columnCount == 2) {
			int i = 0;
			while (!valid()) {
				i++;
				if (i > 200000)
					return false;
				int direction = random.nextInt(4);
				int nextRow = row + MiscUtils.DX4[direction];
				int nextColumn = column + MiscUtils.DY4[direction];
				if (MiscUtils.isValidCell(nextRow, nextColumn, 3, 2)) {
					answer.add(directions[direction ^ 2]);
					board[row][column] = board[nextRow][nextColumn];
					row = nextRow;
					column = nextColumn;
					board[row][column] = 0;
				}
			}
			return true;
		}
		if (rowCount > 3) {
			for (int j = columnCount - 1; j > 0; j--) {
				move(target[rowCount - 1][j], rowCount - 1, j);
				fixed[rowCount - 1][j] = true;
			}
			move(target[rowCount - 1][0], rowCount - 2, 0);
			if (row == rowCount - 1 && column == 0) {
				answer.add("UP");
				fixed[rowCount - 1][0] = true;
				board[rowCount - 1][0] = board[rowCount - 2][0];
				board[rowCount - 2][0] = 0;
				row--;
			} else {
				fixed[rowCount - 2][0] = true;
				goTo(rowCount - 2, 2);
				answer.add("DOWN");
				answer.add("LEFT");
				answer.add("LEFT");
				answer.add("UP");
				answer.add("RIGHT");
				answer.add("DOWN");
				answer.add("RIGHT");
				answer.add("UP");
				board[rowCount - 2][0] = board[rowCount - 2][1];
				board[rowCount - 2][1] = board[rowCount - 1][0];
				board[rowCount - 1][0] = target[rowCount - 1][0];
				fixed[rowCount - 1][0] = true;
				fixed[rowCount - 2][0] = false;
			}
			return go(rowCount - 1, columnCount);
		}
		if (columnCount > 2) {
			for (int j = rowCount - 1; j > 0; j--) {
				move(target[j][columnCount - 1], j, columnCount - 1);
				fixed[j][columnCount - 1] = true;
			}
			move(target[0][columnCount - 1], 0, columnCount - 2);
			if (row == 0 && column == columnCount - 1) {
				answer.add("LEFT");
				fixed[0][columnCount - 1] = true;
				board[0][columnCount - 1] = board[0][columnCount - 2];
				board[0][columnCount - 2] = 0;
				column--;
			} else {
				fixed[0][columnCount - 2] = true;
				goTo(2, columnCount - 2);
				answer.add("RIGHT");
				answer.add("UP");
				answer.add("UP");
				answer.add("LEFT");
				answer.add("DOWN");
				answer.add("RIGHT");
				answer.add("DOWN");
				answer.add("LEFT");
				board[0][columnCount - 2] = board[1][columnCount - 2];
				board[1][columnCount - 2] = board[0][columnCount - 1];
				board[0][columnCount - 1] = target[0][columnCount - 1];
				fixed[0][columnCount - 1] = true;
				fixed[0][columnCount - 2] = false;
			}
			return go(rowCount, columnCount - 1);
		}
		throw new RuntimeException();
	}

	private boolean valid() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				if (board[i][j] != target[i][j])
					return false;
			}
		}
		return true;
	}

	private void move(int what, int targetRow, int targetColumn) {
		while (true) {
			int curRow = -1;
			int curColumn = -1;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] == what) {
						curRow = i;
						curColumn = j;
					}
				}
			}
			if (curRow == targetRow && curColumn == targetColumn)
				return;
			fixed[curRow][curColumn] = true;
			goTo(targetRow, targetColumn);
			fixed[curRow][curColumn] = false;
			goTo(curRow, curColumn);
		}
	}

	private void goTo(int targetRow, int targetColumn) {
		if (targetRow == row && targetColumn == column)
			return;
		ArrayUtils.fill(visited, false);
		direction[row][column] = -1;
		visited[targetRow][targetColumn] = true;
		queueRow[0] = targetRow;
		queueColumn[0] = targetColumn;
		int size = 1;
		for (int i = 0; i < size; i++) {
			int curRow = queueRow[i];
			int curColumn = queueColumn[i];
			for (int j = 0; j < 4; j++) {
				int nextRow = curRow + MiscUtils.DX4[j];
				int nextColumn = curColumn + MiscUtils.DY4[j];
				if (MiscUtils.isValidCell(nextRow, nextColumn, board.length, board.length) && !fixed[nextRow][nextColumn] && !visited[nextRow][nextColumn]) {
					visited[nextRow][nextColumn] = true;
					queueRow[size] = nextRow;
					queueColumn[size++] = nextColumn;
					direction[nextRow][nextColumn] = j;
				}
			}
		}
		if (direction[row][column] == -1)
			throw new RuntimeException();
		while (row != targetRow || column != targetColumn) {
			answer.add(directions[direction[row][column]]);
			int nextRow = row + MiscUtils.DX4[direction[row][column] ^ 2];
			int nextColumn = column + MiscUtils.DY4[direction[row][column] ^ 2];
			board[row][column] = board[nextRow][nextColumn];
			row = nextRow;
			column = nextColumn;
		}
		board[row][column] = 0;
	}
}
