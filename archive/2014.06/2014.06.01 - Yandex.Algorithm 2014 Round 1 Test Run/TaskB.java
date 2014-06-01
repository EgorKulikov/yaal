package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] tone = IOUtils.readIntArray(in, count);
		Arrays.sort(tone);
		long[] answer = new long[count + 1];
		for (int i = count - 1; i >= 0; i--) {
			answer[i] = Long.MAX_VALUE;
			if (i != count - 1)
				answer[i] = Math.min(answer[i], Math.abs(tone[i] - tone[i + 1]) + answer[i + 2]);
			if (i != 0)
				answer[i] = Math.min(answer[i], Math.abs(tone[i] - tone[i - 1]) + answer[i + 1]);
		}
		out.printLine(answer[0]);
    }
}
