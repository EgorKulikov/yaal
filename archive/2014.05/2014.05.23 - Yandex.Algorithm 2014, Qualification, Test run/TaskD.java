package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		for (int i = 1; i < count; i += 2)
			array[i] *= -1;
		long minSum = 0;
		long maxSum = 0;
		long current = 0;
		long answer = 0;
		for (int i : array) {
			current += i;
			minSum = Math.min(minSum, current);
			maxSum = Math.max(maxSum, current);
			answer = Math.max(answer, Math.max(maxSum - current, current - minSum));
		}
		out.printLine(answer);
    }
}
