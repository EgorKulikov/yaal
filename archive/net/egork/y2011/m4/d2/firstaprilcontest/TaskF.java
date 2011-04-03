package net.egork.y2011.m4.d2.firstaprilcontest;

import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class TaskF implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		List<String> input = new ArrayList<String>();
		try {
			//noinspection InfiniteLoopStatement
			while (true)
				input.add(in.readLine(false));
		} catch (InputMismatchException ignored) {
		}
		char[][] table = new char[input.size()][];
		for (int i = 0; i < table.length; i++)
			table[i] = input.get(i).toCharArray();
		int xCount = 0;
		int oCount = 0;
		for (char[] row : table) {
			for (char cell : row) {
				if (cell == 'X')
					xCount++;
				else if (cell == 'O')
					oCount++;
			}
		}
		if (xCount > oCount + 1 || xCount < oCount) {
			out.println("INCORRECT");
			return;
		}
		if (xCount > oCount) {
			for (char[] row : table) {
				for (int i = 0; i < row.length; i++) {
					if (row[i] == 'X') {
						row[i] = '.';
						if (!ended(table)) {
							out.println("CORRECT");
							return;
						}
						row[i] = 'X';
					}
				}
			}
		} else {
			for (char[] row : table) {
				for (int i = 0; i < row.length; i++) {
					if (row[i] == 'O') {
						row[i] = '.';
						if (!ended(table)) {
							out.println("CORRECT");
							return;
						}
						row[i] = 'O';
					}
				}
			}
		}
		out.println("INCORRECT");
	}

	private boolean ended(char[][] table) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (table[i][j] == 'X' || table[i][j] == 'O') {
					for (int direction = 0; direction < 4; direction++) {
						int dx, dy;
						if (direction == 0) {
							dx = 0;
							dy = 1;
						} else {
							dx = 1;
							dy = direction - 2;
						}
						boolean win = true;
						for (int k = 1; k < 5 && win; k++) {
							int ni = i + dx * k;
							int nj = j + dy * k;
							if (!(ni >= 0 && ni < table.length && nj >= 0 && nj < table[ni].length && table[ni][nj] == table[i][j]))
								win = false;
						}
						if (win)
							return true;
					}
				}
			}
		}
		return false;
	}
}

