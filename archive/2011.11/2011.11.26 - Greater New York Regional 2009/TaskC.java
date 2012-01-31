package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private int[][] answer = new int[51][1001];

	{
		for (int i = 1; i <= 1000; i++)
			answer[1][i] = i;
		for (int i = 2; i <= 50; i++) {
			for (int j = 1; j <= 1000; j++) {
				answer[i][j] = Integer.MAX_VALUE;
				for (int k = 1; k <= j; k++)
					answer[i][j] = Math.min(answer[i][j], Math.max(answer[i - 1][k - 1], answer[i][j - k]) + 1);
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int ballCount = in.readInt();
		int floorCount = in.readInt();
		out.printLine(testNumber, answer[ballCount][floorCount]);
	}
}
