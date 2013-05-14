package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int expected = in.readInt();
		int actual = in.readInt();
		int count = in.readInt();
		int[] cost = IOUtils.readIntArray(in, count);
		int requiredDiff = actual - expected;
		int answer = 0;
		for (int i = 0; i < count; i++) {
			boolean[] possible = new boolean[Math.max(expected, actual) + 1];
			possible[0] = true;
			for (int j = 0; j < possible.length; j++) {
				if (!possible[j])
					continue;
				for (int k = 0; k < count; k++) {
					if (k != i && j + cost[k] < possible.length)
						possible[j + cost[k]] = true;
				}
			}
			for (int j = 0; j < count; j++) {
				if (i == j)
					continue;
				int diff = cost[j] - cost[i];
				if (requiredDiff * diff > 0 && requiredDiff % diff == 0) {
					int quantity = requiredDiff / diff;
					int inOtherMoney = actual - quantity * cost[j];
					if (inOtherMoney >= 0 && possible[inOtherMoney])
						answer++;
				}
			}
		}
		out.printLine(answer);
	}
}
