package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	private int[][][] answer = new int[101][101][2];

	{
		answer[0][0][0] = 1;
		for (int i = 1; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				answer[i][j][0] = answer[i - 1][j][0] + answer[i - 1][j][1];
				answer[i][j][1] = answer[i - 1][j][0];
				if (j != 0)
					answer[i][j][1] += answer[i - 1][j - 1][1];
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int bitCount = in.readInt();
		int adjacentCount = in.readInt();
		out.printLine(testNumber, answer[bitCount][adjacentCount][0] + answer[bitCount][adjacentCount][1]);
	}
}
