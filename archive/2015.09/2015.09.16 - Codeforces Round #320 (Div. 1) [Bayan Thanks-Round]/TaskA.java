package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		if (b > a) {
			out.printLine(-1);
			return;
		}
		double answer = Double.POSITIVE_INFINITY;
		int c2 = (a + b) / (2 * b);
		if (c2 != 0) {
			answer = Math.min(answer, (a + b) / (2d * c2));
		}
		int c1 = (a - b) / (2 * b);
		if (c1 != 0 && answer > (a - b) / (2d * c1)) {
			answer = Math.min(answer, (a - b) / (2d * c1));
			throw new RuntimeException();
		}
		out.printLine(answer);
	}
}
