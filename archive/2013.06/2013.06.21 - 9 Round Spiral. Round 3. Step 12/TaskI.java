package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] importance = IOUtils.readIntArray(in, count);
		Arrays.sort(importance);
		int from = 0;
		int answer = 0;
		for (int i = 1; i < count; i++) {
			if (2 * importance[i - 1] <= importance[i]) {
				answer = Math.max(answer, i - from);
				from = i;
			}
		}
		answer = Math.max(answer, count - from);
		out.printLine(answer);
	}
}
