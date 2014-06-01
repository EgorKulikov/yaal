package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] value = IOUtils.readIntArray(in, count);
		long[] current = new long[1 << 16];
		long[] next = new long[1 << 16];
		current[0] = 1;
		for (int i = 0; i < count; i++) {
			Arrays.fill(next, 0);
			if (i % 19 == 0) {
				for (int j = 0; j < current.length; j++)
					current[j] %= MOD;
			}
			for (int j = 0; j < current.length; j++) {
				next[j] += current[j];
				next[j ^ value[i]] += 2 * current[j];
			}
			long[] temp = current;
			current = next;
			next = temp;
		}
		out.printLine(current[0] % MOD);
    }
}
