package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int operations = in.readInt();
		int count = in.readInt();
		int[] data = IOUtils.readIntArray(in, count);
		if (count == 1) {
			out.printLine(data[0]);
			return;
		}
		if (count > operations) {
			Arrays.sort(data);
			out.printLine(data[operations]);
			return;
		}
		operations -= count - 1;
		long[] min = new long[count - 1];
		long[] max = new long[count - 1];
		for (int i = 0; i < count - 1; i++) {
			min[i] = Math.min(data[i], data[i + 1]);
			max[i] = Math.max(data[i], data[i + 1]);
		}
		for (int i = 0; i < 47 && i < operations; i++) {
			for (int j = 0; j < count - 1; j++) {
				max[j] += min[j];
				min[j] = max[j] - min[j];
			}
		}
		int at = ArrayUtils.maxPosition(max);
		long cMin = Math.min(data[at], data[at + 1]);
		long cMax = Math.max(data[at], data[at + 1]);
		for (int i = 0; i < operations; i++) {
			cMax += cMin;
			cMin = cMax - cMin;
			if ((i & 31) == 0) {
				cMax %= MOD;
				cMin %= MOD;
			}
		}
		out.printLine(cMax % MOD);
    }
}
