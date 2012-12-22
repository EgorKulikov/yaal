package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x1 = in.readInt();
		int x2 = in.readInt();
		int a = in.readInt();
		int b = in.readInt();
		int delta = x2 - x1;
		if (delta >= a && delta <= b) {
			out.printLine("FIRST");
			out.printLine(x2);
			return;
		}
		if (a <= 0 && b >= 0) {
			out.printLine("DRAW");
			return;
		}
		if (x1 < x2 && b < 0 || x1 > x2 && b > 0) {
			out.printLine("DRAW");
			return;
		}
		if (delta < 0) {
			delta = -delta;
			int temp = -a;
			a = -b;
			b = temp;
		}
		int remainder = delta % (a + b);
		if (remainder == 0)
			out.printLine("SECOND");
		else if (remainder < a || remainder > b)
			out.printLine("DRAW");
		else {
			out.printLine("FIRST");
			if (x1 < x2)
				out.printLine(x1 + remainder);
			else
				out.printLine(x1 - remainder);
		}
	}
}
