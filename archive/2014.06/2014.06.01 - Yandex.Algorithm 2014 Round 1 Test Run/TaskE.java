package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] cost = IOUtils.readIntArray(in, count);
		long required = 0;
		for (int i : cost)
			required |= 1L << i;
		out.printLine(go(0, 1, 1L, required, 6));
    }

	private int go(int step, int last, long current, long required, int result) {
		if ((current & required) == required)
			return step;
		if (step >= result - 1)
			return result;
		for (int i = last; i <= 50; i++)
			result = Math.min(result, go(step + 1, i, current | (current << i), required, result));
		return result;
	}
}
