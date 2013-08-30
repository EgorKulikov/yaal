package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int sum = Math.abs(x) + Math.abs(y);
		if (x < 0)
			out.printLine(-sum, 0, 0, y < 0 ? -sum : sum);
		else
			out.printLine(0, y < 0 ? -sum : sum, sum, 0);
    }
}
