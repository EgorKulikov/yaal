package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x1 = in.readInt();
		int y1 = in.readInt();
		int x2 = in.readInt();
		int y2 = in.readInt();
		int answer = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
		out.printLine(answer);
	}
}
