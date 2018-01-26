package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndTheStones {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n1 = in.readLong();
		long n2 = in.readLong();
		long m = in.readInt();
		long max = m * (m + 1) / 2;
		max = Math.min(max, Math.min(n1, n2));
		n1 -= max;
		n2 -= max;
		out.printLine(n1 + n2);
	}
}
