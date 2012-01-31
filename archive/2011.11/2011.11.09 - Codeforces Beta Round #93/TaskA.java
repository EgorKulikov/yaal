package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long t1 = in.readInt();
		long t2 = in.readInt();
		long x1 = in.readInt();
		long x2 = in.readInt();
		long t0 = in.readInt();
		if (t0 == t1) {
			if (t0 == t2) {
				out.printLine(x1, x2);
				return;
			}
			out.printLine(x1, 0);
			return;
		}
		long leftDelta = t0 - t1;
		long rightDelta = t2 - t0;
		long bestY1 = -1;
		long bestY2 = -1;
		Rational minDelta = Rational.MAX_VALUE;
		for (long y2 = 1; y2 <= x2; y2++) {
			long y1 = Math.min((y2 * rightDelta) / leftDelta, x1);
			Rational curDelta = new Rational(t1 * y1 + t2 * y2 - t0 * y1 - t0 * y2, y1 + y2);
			if (curDelta.compareTo(minDelta) <= 0) {
				minDelta = curDelta;
				bestY1 = y1;
				bestY2 = y2;
			}
		}
		out.printLine(bestY1, bestY2);
	}
}
