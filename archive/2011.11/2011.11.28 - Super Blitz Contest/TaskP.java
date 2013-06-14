package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskP {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x1 = in.readInt();
		int y1 = in.readInt();
		int x2 = in.readInt();
		int y2 = in.readInt();
		int dx = Math.abs(x1 - x2);
		int dy = Math.abs(y1 - y2);
		out.printLine(IntegerUtils.gcd(dx, dy) + 1);
	}
}
