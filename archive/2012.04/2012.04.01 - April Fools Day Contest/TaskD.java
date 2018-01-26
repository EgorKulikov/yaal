package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
	int[] answer = new int[]{2, 3, 1, 2, 1};

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt() - 1;
		out.printLine(answer[index]);
	}
}
