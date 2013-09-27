package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] profit = IOUtils.readIntArray(in, count);
		long answer = Long.MIN_VALUE;
		long min = 0;
		long current = 0;
		for (int i : profit) {
			current += i;
			answer = Math.max(answer, current - min);
			min = Math.min(min, current);
		}
		out.printLine(answer);
    }
}
