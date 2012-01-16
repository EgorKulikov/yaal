package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int answer = 0;
		if (count >= 3)
			answer = (1 << (count - 1)) - count;
		out.printLine(answer);
	}
}
