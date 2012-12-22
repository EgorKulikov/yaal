package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int stepCount = in.readInt();
		if (rowCount == 1 || columnCount == 1) {
			out.printLine(0);
			return;
		}
		long[][] count = new long[stepCount + 1][Math.max(rowCount, columnCount) + 1];
		for (int i = 0; i < count[1].length; i++)
			count[1][i] = i * (i + 1) / 2;
		for (int i = 2; i <= stepCount; i++) {
			long sum = 0;
			long res = 0;
			for (int j = 2; j < count[i].length; j++) {
				sum += count[i - 1][j - 2];
				res += sum;
				res %= MOD;
				count[i][j] = res;
			}
		}
		long answer = count[stepCount][rowCount - 2] * count[stepCount][columnCount - 2] % MOD;
		out.printLine(answer);
	}
}
