package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long a = in.readInt();
		long b = in.readInt();
//		if (a == 1 || b == 1) {
//			a = b = 1;
//		}
		long x1 = in.readInt();
		long y1 = in.readInt();
		long x2 = in.readInt();
		long y2 = in.readInt();
		long deltaX = Math.abs(divide((x1 + y1), (2 * a)) - divide((x2 + y2), (2 * a)));
		long deltaY = Math.abs(divide((x1 - y1), (2 * b)) - divide((x2 - y2), (2 * b)));
		out.printLine(Math.max(deltaX, deltaY));
	}

	private long divide(long x, long y) {
		if (x >= 0)
			return x / y;
		return -(Math.abs(x) + y - 1) / y;
	}
}
