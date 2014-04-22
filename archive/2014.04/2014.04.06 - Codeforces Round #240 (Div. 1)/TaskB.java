package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	long[][] result;
	int max;
	int length;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		max = in.readInt();
		length = in.readInt();
		result = new long[length + 1][max + 1];
		ArrayUtils.fill(result, -1);
		out.printLine(calculate(0, 1));
    }

	private long calculate(int step, int number) {
		if (result[step][number] != -1)
			return result[step][number];
		if (step == length)
			return result[step][number] = 1;
		result[step][number] = 0;
		for (int i = number; i <= max; i += number)
			result[step][number] += calculate(step + 1, i);
		return result[step][number] %= (int)(1e9 + 7);
	}
}
