package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		int[][] matrix = IOUtils.readIntTable(in, size, size);
		int[][] sumsDirect = new int[size + 1][size + 2];
		int[][] sumsReverse = new int[size + 1][size + 2];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sumsDirect[i + 1][j + 1] = sumsDirect[i][j] + matrix[i][j];
				sumsReverse[i + 1][j + 1] = sumsReverse[i][j + 2] + matrix[i][j];
			}
		}
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int max = Math.min(size - i, size - j);
				for (int k = 1; k <= max; k++)
					result = Math.max(result, sumsDirect[i + k][j + k] - sumsDirect[i][j] + sumsReverse[i][j + k + 1] - sumsReverse[i + k][j + 1]);
			}
		}
		out.println(result);
	}
}
