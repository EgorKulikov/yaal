package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[][] answer = new int[3 * n][];
		for (int i = 0; i < n; i++)
			answer[i] = new int[n];
		for (int i = n; i < 2 * n; i++)
			answer[i] = new int[4 * n];
		for (int i = 2 * n; i < 3 * n; i++)
			answer[i] = new int[n];
		int x = 1;
		int y = n * n;
		int z = n * n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer[i + n][j] = x;
				answer[i + n][n - j - 1 + 2 * n] = 5 * z + y;
				answer[i + n][j + n] = x + z;
				answer[i + n][n - j - 1 + 3 * n] = 4 * z + y;
				answer[n - i - 1][j] = 2 * z + x;
				answer[2 * n + i][j] = 3 * z + y;
				x++;
				y--;
			}
		}
		for (int[] row : answer)
			out.printLine(row);
    }
}
