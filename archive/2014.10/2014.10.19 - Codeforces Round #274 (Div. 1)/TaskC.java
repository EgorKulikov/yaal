package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int floorCount = in.readInt();
		int start = in.readInt() - 1;
		int forbidden = in.readInt() - 1;
		int count = in.readInt();
		long[] answer = new long[floorCount];
		Arrays.fill(answer, 1);
		long[] sum = new long[floorCount + 1];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < floorCount; j++) {
				sum[j + 1] = sum[j] + answer[j];
			}
			for (int j = 0; j < floorCount; j++) {
				if (j != forbidden) {
					int delta = Math.abs(forbidden - j);
					answer[j] = (sum[Math.min(j + delta, floorCount)] - sum[Math.max(j - delta + 1, 0)] - answer[j]) % MOD;
				}
			}
		}
		out.printLine(answer[start]);
    }
}
