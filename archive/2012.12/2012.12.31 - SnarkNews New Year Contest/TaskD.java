package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int width = in.readInt();
		in.readInt();
		in.readInt();
		int dx = in.readInt();
		int dy = in.readInt();
		double answer = (double)width / dx * Math.hypot(dx, dy);
		out.printLine(answer);
    }
}
