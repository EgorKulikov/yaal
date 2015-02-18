package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JimAndTheChallenge {
	private static final long MOD = 1000000009;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int dimensionCount = in.readInt();
		int[] weight = new int[count];
		int[][] x = new int[dimensionCount][count];
		for (int i = 0; i < count; i++) {
			weight[i] = in.readInt();
			for (int j = 0; j < dimensionCount; j++) {
				x[j][i] = in.readInt();
			}
		}
		long answer = 0;
		for (int i = 0; i < dimensionCount; i++) {
			int[] order = ArrayUtils.order(x[i]);
			long delta = 0;
			long weightSum = 0;
			for (int j : order) {
				answer += (weightSum * x[i][j] - delta) % MOD * weight[j] % MOD;
				delta += (long)x[i][j] * weight[j];
				delta %= MOD;
				weightSum += weight[j];
				weightSum %= MOD;
			}
		}
		answer %= MOD;
		if (answer < 0) {
			answer += MOD;
		}
		out.printLine(answer);
	}
}
