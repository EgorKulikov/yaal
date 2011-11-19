package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int variableCount = in.readInt();
		String[] from = new String[variableCount];
		String[] to = new String[variableCount];
		IOUtils.readStringArrays(in, from, to);
		long[][] count = new long[variableCount][100001];
		ArrayUtils.fill(count, 1);
		long result = 1;
		for (int i = variableCount - 1; i >= 0; i--) {
			if (Character.isLetter(from[i].charAt(0))) {
				int index = from[i].charAt(0) - 'a';
				int right = Integer.parseInt(to[i]);
				for (int j = right + 1; j <= 100000; j++)
					count[index][j] = 0;
				long sum = 0;
				for (int j = right; j >= 0; j--) {
					sum += count[i][j];
					if (sum >= MOD)
						sum -= MOD;
					count[index][j] = count[index][j] * sum % MOD;
				}
			} else if (Character.isLetter(to[i].charAt(0))) {
				int index = to[i].charAt(0) - 'a';
				int left = Integer.parseInt(from[i]);
				for (int j = 0; j < left; j++)
					count[index][j] = 0;
				long sum = 0;
				for (int j = left; j <= 100000; j++) {
					sum += count[i][j];
					if (sum >= MOD)
						sum -= MOD;
					count[index][j] = count[index][j] * sum % MOD;
				}
			} else {
				long current = 0;
				int left = Integer.parseInt(from[i]);
				int right = Integer.parseInt(to[i]);
				for (int j = left; j <= right; j++)
					current += count[i][j];
				current %= MOD;
				result = result * current % MOD;
			}
		}
		out.printLine(result);
	}
}
