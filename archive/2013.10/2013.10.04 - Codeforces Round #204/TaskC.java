package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int repeats = in.readInt();
		int[] openCost = IOUtils.readIntArray(in, count);
		int[] closeCost = IOUtils.readIntArray(in, count);
		long[][][] answer = new long[count + 1][2 * count + 1][count + 1];
		ArrayUtils.fill(answer, Long.MAX_VALUE);
		answer[0][count][0] = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j <= 2 * count; j++) {
				for (int k = 0; k < count; k++) {
					if (answer[i][j][k] == Long.MAX_VALUE)
						continue;
					answer[i + 1][j + 1][k] = Math.min(answer[i + 1][j + 1][k], answer[i][j][k] + openCost[i]);
					answer[i + 1][j - 1][Math.max(k, count - j + 1)] = Math.min(answer[i + 1][j - 1][Math.max(k, count - j + 1)], answer[i][j][k] + closeCost[i]);
				}
			}
		}
		long[][] delta = answer[count];
		long[][] matrix = new long[3 * count + 1][3 * count + 1];
		ArrayUtils.fill(matrix, Long.MAX_VALUE / 2);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = Math.max(0, count - i); j <= 2 * count && i + j - count < matrix.length; j++) {
				for (int k = 0; k <= i && k <= count; k++)
					matrix[i][i + j - count] = Math.min(matrix[i][i + j - count], delta[j][k]);
			}
		}
		long[][] result = new long[matrix.length][matrix.length];
		long[][] temp = new long[matrix.length][matrix.length];
		power(repeats, result, temp, matrix);
		out.printLine(result[0][0]);
    }

	private void power(int exponent, long[][] result, long[][] temp, long[][] matrix) {
		if (exponent == 1) {
			for (int i = 0; i < matrix.length; i++)
				System.arraycopy(matrix[i], 0, result[i], 0, matrix.length);
			return;
		}
		if ((exponent & 1) == 0) {
			power(exponent >> 1, temp, result, matrix);
			multiply(result, temp, temp);
		} else {
			power(exponent - 1, temp, result, matrix);
			multiply(result, temp, matrix);
		}
	}

	private void multiply(long[][] a, long[][] b, long[][] c) {
		ArrayUtils.fill(a, Long.MAX_VALUE / 2);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				for (int k = 0; k < a.length; k++)
					a[i][k] = Math.min(a[i][k], b[i][j] + c[j][k]);
			}
		}
	}

	private int[] solve(int count, int repeats, int[][] delta, int[] current) {
		int[] next = new int[count + 1];
		for (int i = 0; i < repeats; i++) {
			Arrays.fill(next, Integer.MAX_VALUE);
			for (int j = 0; j <= count; j++) {
				if (current[j] == Integer.MAX_VALUE)
					continue;
				for (int k = count - j; k <= 2 * count - j; k++) {
					for (int l = 0; l <= j; l++) {
						if (delta[k][l] == Integer.MAX_VALUE)
							continue;
						next[j + k - count] = Math.min(next[j + k - count], current[j] + delta[k][l]);
					}
				}
			}
			int[] temp = current;
			current = next;
			next = temp;
		}
		return current;
	}
}
