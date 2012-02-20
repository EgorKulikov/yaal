package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Checkpoint {
	static int[] minTravelled = new int[10000001];

	static {
		for (int i = 1; i <= 10000000; i++)
			minTravelled[i] = i;
		int[][] c = new int[5001][5001];
		c[0][0] = 1;
		for (int i = 1; i <= 5000; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				c[i][j] = Math.min(c[i - 1][j - 1] + c[i - 1][j], 10000001);
				if (c[i][j] <= 10000000)
					minTravelled[c[i][j]] = Math.min(minTravelled[c[i][j]], i);
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i * i <= count; i++) {
			if (count % i == 0)
				answer = Math.min(answer, minTravelled[i] + minTravelled[count / i]);
		}
		out.printLine("Case #" + testNumber + ":", answer);
	}
}
