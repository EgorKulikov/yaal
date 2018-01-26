package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long answer = 1;
		for (int i = 1; i < n; i++)
			answer += 12 * i;
		out.printLine(answer);
	}
}
