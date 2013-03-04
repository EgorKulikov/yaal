package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int surprising = in.readInt();
		int required = in.readInt();
		int[] scores = IOUtils.readIntArray(in, count);
		int answer = 0;
		for (int i : scores) {
			if (i < required)
				continue;
			if (i >= 3 * required - 2)
				answer++;
			else if (i >= 3 * required - 4 && surprising > 0) {
				answer++;
				surprising--;
			}
		}
		out.printLine("Case #" + testNumber + ":", answer);
	}
}
