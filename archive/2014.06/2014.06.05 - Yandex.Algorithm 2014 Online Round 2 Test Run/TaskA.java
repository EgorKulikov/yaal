package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int minDistance = in.readInt();
		int[] lengths = IOUtils.readIntArray(in, count);
		long answer = 0;
		int delta = 0;
		for (int i : lengths) {
			answer += i / minDistance;
			delta += (i + 1) / minDistance - i / minDistance;
		}
		if (minDistance % 2 == 0)
			answer += delta;
		else
			answer += Math.min(delta, 1);
		out.printLine(answer);
    }
}
