package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] perUniversity = IOUtils.readIntArray(in, count);
		int maxPerUniversity = in.readInt();
		int answer = 0;
		for (int i : perUniversity)
			answer += Math.min(i, maxPerUniversity);
		out.printLine(answer);
	}
}
