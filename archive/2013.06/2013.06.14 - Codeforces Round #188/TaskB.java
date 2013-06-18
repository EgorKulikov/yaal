package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int n = 70;
		int[][] answer = new int[2 * n + 1][2 * n + 1];
		answer[n][n] = count;
		while (true) {
			boolean update = false;
			for (int i = 0; i <= 2 * n; i++) {
				for (int j = 0; j <= 2 * n; j++) {
					if (answer[i][j] >= 4) {
						update = true;
						answer[i - 1][j] += answer[i][j] >> 2;
						answer[i + 1][j] += answer[i][j] >> 2;
						answer[i][j - 1] += answer[i][j] >> 2;
						answer[i][j + 1] += answer[i][j] >> 2;
						answer[i][j] &= 3;
					}
				}
			}
			if (!update)
				break;
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			if (Math.abs(x) <= n && Math.abs(y) <= n)
				out.printLine(answer[x + n][y + n]);
			else
				out.printLine(0);
		}
	}
}
