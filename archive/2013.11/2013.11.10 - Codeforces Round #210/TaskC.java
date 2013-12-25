package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int beauty = in.readInt();
		char[] sample = IOUtils.readCharArray(in, length);
		for (int i = 0; i < length; i++)
			sample[i] -= 'a';
		long[][] more = new long[length][beauty + 1];
		long[][] less = new long[length][beauty + 1];
		less[length - 1][0] = sample[length - 1] + 1;
		if (beauty > 0)
			more[length - 1][1] = 25 - sample[length - 1];
		for (int i = length - 1; i > 0; i--) {
			for (int j = 0; j <= beauty; j++) {
				if (less[i][j] != 0) {
					less[i][j] %= MOD;
					less[i - 1][j] += less[i][j] * (sample[i - 1] + 1);
					if (j + (length - i + 1) <= beauty)
						more[i - 1][j + (length - i + 1)] += less[i][j] * (25 - sample[i - 1]);
				}
				if (more[i][j] != 0) {
					more[i][j] %= MOD;
					int total = j;
					for (int k = i - 1; total <= beauty && k >= 0; k--) {
						less[k][total] += more[i][j] * sample[k];
						if (total + (length - k) <= beauty)
							more[k][total + (length - k)] += more[i][j] * (25 - sample[k]);
						total += length - i;
					}
					if (total <= beauty)
						less[0][total] += more[i][j];
				}
			}
		}
		long answer = (more[0][beauty] + less[0][beauty]) % MOD;
		out.printLine(answer);
    }
}
