package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		if (first + second != 100)
			out.printLine(first + second + 1);
		else
			out.printLine(first + second - 1);
    }
}
