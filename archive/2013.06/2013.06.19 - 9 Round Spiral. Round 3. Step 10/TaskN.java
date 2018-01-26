package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskN {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int a = in.readInt();
		int b = in.readInt();
		out.printLine(a, b, -a * x - b * y);
    }
}
