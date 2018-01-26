package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskM {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x0 = in.readInt();
		int y0 = in.readInt();
		int x1 = in.readInt();
		int y1 = in.readInt();
		int a = y0 - y1;
		int b = x1 - x0;
		int c = -a * x0 - b * y0;
		out.printLine(-a, -b, -c);
    }
}
