package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long a = in.readLong();
		long b = in.readLong();
		int index = (int) (in.readLong() % 5);
		if (a == 0 && b == 0)
			throw new UnknownError();
		if (index == 0)
			out.printLine(a);
		else if (index == 1)
			out.printLine(b);
		else if (index == 2)
			out.printLine((b + 1) / a);
		else if (index == 3)
			out.printLine((a + b + 1) / a / b);
		else if (index == 4)
			out.printLine((a + 1) / b);
	}
}
