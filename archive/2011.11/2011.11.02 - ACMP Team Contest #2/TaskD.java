package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long denominator = in.readLong();
		while (denominator % 2 == 0)
			denominator /= 2;
		while (denominator % 5 == 0)
			denominator /= 5;
		if (denominator == 1)
			out.printLine("NO");
		else
			out.printLine("YES");
	}
}
