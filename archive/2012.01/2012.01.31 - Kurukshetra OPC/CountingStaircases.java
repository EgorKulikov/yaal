package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CountingStaircases {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] matrix = IOUtils.readIntTable(in, size, size);
		int[][] maxSize = new int[size][size];
		int[][] right = new int[size][size];
		int[][] up = new int[size][size];
		int answer = 0;
		int mod = (int)1e6 + 3;
		for (int i = 0; i < size; i++) {
			for (int j = size - 1; j >= 0; j--) {
				if (matrix[i][j] == 0)
					continue;
				if (j == size - 1)
					right[i][j] = 1;
				else
					right[i][j] = right[i][j + 1] + 1;
				if (i == 0)
					up[i][j] = 1;
				else
					up[i][j] = up[i - 1][j] + 1;
				if (j == size - 1 || i == 0)
					maxSize[i][j] = 1;
				else
					maxSize[i][j] = Math.min(maxSize[i - 1][j + 1] + 2, Math.min(up[i][j], right[i][j]));
				answer += maxSize[i][j] - 1;
				answer %= mod;
			}
		}
		out.printLine(answer);
	}
}
