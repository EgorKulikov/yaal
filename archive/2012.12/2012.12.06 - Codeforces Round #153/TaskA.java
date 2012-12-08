package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int delta = in.readInt();
		int[] points = IOUtils.readIntArray(in, count);
		int j = 0;
		long answer = 0;
		for (int i = 0; i < count; i++) {
			while (points[i] - points[j] > delta)
				j++;
			long curCount = i - j;
			answer += curCount * (curCount - 1) / 2;
		}
		out.printLine(answer);
	}
}
