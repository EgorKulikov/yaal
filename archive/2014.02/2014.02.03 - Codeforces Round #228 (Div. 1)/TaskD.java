package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final long MOD = 1000000007;
	long[][][] answer;
	private int upTo;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		upTo = in.readInt();
		answer = new long[2][30][31];
		ArrayUtils.fill(answer, -1);
		long result = go(1, 29, 0);
		out.printLine(result);
    }

	private long go(int full, int bit, int groups) {
		if (groups < 0)
			return 0;
		if (bit == -1)
			return 1;
		if (answer[full][bit][groups] != -1)
			return answer[full][bit][groups];
		long result;
		if (full == 0 || (upTo >> bit & 1) == 1) {
			if (groups != 0)
				result = go(0, bit - 1, groups) * (1 << (groups - 1)) + go(full, bit - 1, groups) * (1 << (groups - 1)) + go(full, bit - 1, groups + 1);
			else
				result = go(full, bit - 1, 1) + go(0, bit - 1, groups);
		} else if ((upTo >> bit & 1) == 0 && groups != 0)
			result = go(full, bit - 1, groups) * (1 << (groups - 1));
		else
			result = go(full, bit - 1, groups);
		return answer[full][bit][groups] = result % MOD;
	}
}
