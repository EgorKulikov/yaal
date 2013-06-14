package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int[][] count = new int[x + 1][y + 1];
		count[0][0] = 1;
		for (int i = 0; i <= x; i++) {
			for (int j = 0; j <= y; j++) {
				if (i * j % 2 != 0 || i < j)
					continue;
				if (i > 0)
					count[i][j] += count[i - 1][j];
				if (j > 0)
					count[i][j] += count[i][j - 1];
				if (count[i][j] >= 1000000007)
					count[i][j] -= 1000000007;
			}
		}
		out.printLine(count[x][y]);
	}
}
