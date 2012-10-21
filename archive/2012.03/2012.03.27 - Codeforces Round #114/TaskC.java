package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long a = in.readLong();
		long b = in.readLong();
		if (go(Math.max(a, b), Math.min(a, b)))
			out.printLine("First");
		else
			out.printLine("Second");
	}

	private boolean go(long a, long b) {
		if (b == 0)
			return false;
		if (!go(b, a % b))
			return true;
		if (b % 2 == 1)
			return a / b % 2 == 0;
		a /= b;
		return (a % (b + 1)) % 2 == 0;
	}
}
