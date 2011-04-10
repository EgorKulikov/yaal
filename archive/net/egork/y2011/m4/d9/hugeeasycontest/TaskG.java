package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.arrays.ArrayUtils;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class TaskG implements Solver {
	private static final int MOD = 1000007;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		char[][] field = in.readTable(size, size);
		int[][] answer = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (field[i][j] == 'W')
					answer[i][j] = 1;
			}
		}
		for (int i = size - 2; i >= 0; i--) {
			for (int j = 0; j < size; j++) {
				if (field[i][j] == 'B')
					continue;
				for (int k = -1; k <= 1; k += 2) {
					int oldRow = i + 1;
					int oldColumn = j + k;
					if (oldColumn >= 0 && oldColumn < size) {
						if (field[oldRow][oldColumn] != 'B')
							answer[i][j] = (answer[i][j] + answer[oldRow][oldColumn]) % MOD;
						else {
							oldRow = i + 2;
							oldColumn = j + 2 * k;
							if (oldColumn >= 0 && oldColumn < size && oldRow < size && field[oldRow][oldColumn] != 'B')
								answer[i][j] = (answer[i][j] + answer[oldRow][oldColumn]) % MOD;
						}
					}
				}
			}
		}
		out.println("Case " + testNumber + ": " + ArrayUtils.sumArray(answer[0]) % MOD);
	}
}

