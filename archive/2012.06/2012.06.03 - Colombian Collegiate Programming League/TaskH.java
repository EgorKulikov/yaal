package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		if (first == -1 && second == -1)
			throw new UnknownError();
		out.printLine(Math.min(Math.abs(first - second), 100 - Math.abs(first - second)));
	}
}
