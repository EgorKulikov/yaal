package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int[] profit = IOUtils.readIntArray(in, length);
		int answer = Integer.MIN_VALUE;
		int minimum = 0;
		int current = 0;
		for (int p : profit) {
			current += p;
			answer = Math.max(answer, current - minimum);
			minimum = Math.min(minimum, current);
		}
		out.printLine(answer);
	}
}
