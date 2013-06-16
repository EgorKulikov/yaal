package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE1 {
	private static final long MOD = (long) 1e9;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		long[] array = IOUtils.readLongArray(in, count);
		long[] f = IntegerUtils.generateFibonacci(count, MOD);
		int sq = (int) Math.round(Math.sqrt(count));
		long[][] result = new long[sq][sq];
		for (int i = 0; i < sq; i++) {
			for (int j = 0; j < sq; j++) {

			}
		}
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				int index = in.readInt() - 1;
				array[index] = in.readInt();
			} else {
				int from = in.readInt() - 1;
				int to = in.readInt() - 1;
				long answer = 0;
				for (int j = from; j <= to; j++)
					answer += array[j] * f[j - from] % MOD;
				out.printLine(answer % MOD);
			}
		}
    }
}
