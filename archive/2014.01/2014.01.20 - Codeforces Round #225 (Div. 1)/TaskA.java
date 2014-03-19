package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] side = IOUtils.readIntArray(in, count);
		int toRight = 0;
		long answer = 0;
		for (int i : side) {
			if (i == 0)
				answer += toRight;
			else
				toRight++;
		}
		out.printLine(answer);
    }
}
