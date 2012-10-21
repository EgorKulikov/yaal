package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		int answer = Math.min(first, second);
		if (answer != 1 && answer % 2 == 1)
			answer--;
		out.printLine(answer);
	}
}
