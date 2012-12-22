package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 1 || count % 2 == 0) {
			out.printLine(-1);
			return;
		}
		int[] coins = IOUtils.readIntArray(in, count);
		int answer = 0;
		for (int i = count - 1; i >= 0; i--) {
			answer += coins[i];
			if (i != 0) {
				if (i % 2 == 0)
					coins[i - 1] = Math.max(coins[i - 1] - coins[i], 0);
				coins[(i - 1) / 2] = Math.max(coins[(i - 1) / 2] - coins[i], 0);
			}
		}
		out.printLine(answer);
	}
}
