package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1125 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = in.readTable(rowCount, columnCount);
		int[][] count = in.readIntTable(rowCount, columnCount);
		long[][] changeCount = new long[rowCount][columnCount];
		boolean[] isSquare = new boolean[10001];
		for (int i = 0; i <= 100; i++)
			isSquare[i * i] = true;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				for (int k = 0; k < rowCount; k++) {
					for (int l = 0; l < columnCount; l++) {
						int dRow = i - k;
						int dColumn = j - l;
						if (isSquare[dRow * dRow + dColumn * dColumn])
							changeCount[k][l] += count[i][j];
					}
				}
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				out.print((changeCount[i][j] % 2 == 0) ? table[i][j] : ((char)('W' + 'B' - table[i][j])));
			out.println();
		}
	}
}

