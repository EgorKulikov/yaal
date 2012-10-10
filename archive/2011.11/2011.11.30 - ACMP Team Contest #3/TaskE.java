package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long capacity = in.readInt();
		long time = in.readInt();
		long count = in.readInt();
		long answer = Math.max((2 * count + capacity - 1) / capacity, 2) * time;
		out.printLine(answer);
	}
}
