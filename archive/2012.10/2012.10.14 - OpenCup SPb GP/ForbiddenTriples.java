package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ForbiddenTriples {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		long answer = n * n * 3;
		if (n % 2 == 0)
			answer -= 6 * n - 4;
		else
			answer -= 3 * n - 1;
		out.printLine(answer);
	}
}
