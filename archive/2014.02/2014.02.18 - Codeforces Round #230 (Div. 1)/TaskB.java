package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[][] matrix = IOUtils.readIntTable(in, 3, 3);
		int size = in.readInt();
		long[][][] answer = new long[size + 1][3][3];
		for (int i = 1; i <= size; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (j == k)
						continue;
					answer[i][j][k] = Math.min(answer[i - 1][j][3 - j - k] + answer[i - 1][3 - j - k][k] + matrix[j][k],
						answer[i - 1][j][k] + matrix[j][3 - j - k] + answer[i - 1][k][j] + matrix[3 - j - k][k] + answer[i - 1][j][k]);
				}
			}
//			for (int j = 0; j < 3; j++) {
//				for (int k = 0; k < 3; k++) {
//					if (j == k)
//						continue;
//					answer[i][j][k] = Math.min(answer[i][j][k], answer[i][j][3 - j - k] + answer[i][3 - j - k][k]);
//				}
//			}
		}
		out.printLine(answer[size][0][2]);
    }
}
