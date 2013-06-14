package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count * (count + 1) / 2 % 2 == 0)
			out.printLine("black");
		else
			out.printLine("grimy");
	}
}
