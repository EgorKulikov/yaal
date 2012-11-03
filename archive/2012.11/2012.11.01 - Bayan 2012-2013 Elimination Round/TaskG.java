package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(305);
		out.printLine(0, 1000000);
		int lastRadius = 1000000;
		int x = 0;
		for (int i = 303; i > 0; i--) {
			int j;
			for (j = 0; j * j < 4 * lastRadius * i; j++);
			out.printLine(x + j, i);
			x += j;
			lastRadius = i;
		}
		out.printLine(999999, 1000000);
	}
}
