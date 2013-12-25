package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FindTheOperations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int distance = in.readInt();
		int[][] map = IOUtils.readIntTable(in, size, size);
		boolean[][] matrix = new boolean[size * size][size * size + 1];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					for (int l = 0; l < size; l++) {
						if ((Math.abs(i - k) + Math.abs(j - l) <= distance))
							matrix[i * size + j][k * size + l] = true;
					}
				}
				if (map[i][j] == 1)
					matrix[i * size + j][size * size] = true;
			}
		}
		int l = 0;
		for (int i = 0; i < size * size; i++) {
			int at = -1;
			for (int j = l; j < size * size; j++) {
				if (matrix[j][i]) {
					at = j;
					break;
				}
			}
			if (at == -1)
				continue;
			for (int j = i; j <= size * size; j++) {
				boolean temp = matrix[l][j];
				matrix[l][j] = matrix[at][j];
				matrix[at][j] = temp;
			}
			for (int j = 0; j < size * size; j++) {
				if (l == j || !matrix[j][i])
					continue;
				for (int k = size * size; k >= i; k--)
					matrix[j][k] ^= matrix[l][k];
			}
			l++;
		}
		for (int i = l; i < size * size; i++) {
			if (matrix[i][size * size]) {
				out.printLine("Impossible");
				return;
			}
		}
		out.printLine("Possible");
		int qty = 0;
		for (int i = 0; i < l; i++) {
			if (matrix[i][size * size])
				qty++;
		}
		out.printLine(qty);
		for (int i = 0; i < l; i++) {
			if (!matrix[i][size * size])
				continue;
			for (int j = 0; j < size * size; j++) {
				if (matrix[i][j]) {
					out.printLine(j / size, j % size);
					break;
				}
			}
		}
    }
}
