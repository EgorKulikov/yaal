package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long coal = in.readLong();
		long hydrogen = in.readLong();
		long oxygen = in.readLong();
		long answer = Math.min(Math.min(coal / 2, hydrogen / 6), oxygen);
		out.printLine(answer);
	}
}
