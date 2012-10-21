package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			int sum = 0;
			for (int j = i; j < count; j++) {
				sum += values[j];
				answer = Math.min(answer, sum);
			}
		}
		out.printLine(answer);
	}
}
