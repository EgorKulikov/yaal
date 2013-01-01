package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		out.printLine(count / 2);
		for (char c1 = 'A', c2 = (char) ('A' + count - 1); c1 < c2; c1++, c2--)
			out.printLine(c1, c2);
    }
}
