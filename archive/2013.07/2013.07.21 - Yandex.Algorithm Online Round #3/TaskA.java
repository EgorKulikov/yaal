package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] taste = IOUtils.readIntArray(in, count);
		int bad = 0;
		for (int i : taste) {
			if (i < 0)
				bad++;
		}
		double answer = 0;
		for (int i : taste) {
			if (i >= 0)
				answer += (double)i / (bad + 1);
			else
				answer += (double)i / bad;
		}
		out.printLine(answer);
    }
}
