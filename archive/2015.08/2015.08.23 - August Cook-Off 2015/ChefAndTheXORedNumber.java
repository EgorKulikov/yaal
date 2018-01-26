package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndTheXORedNumber {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 1) {
			out.printLine(2);
		} else if (((n + 1) & n) == 0) {
			out.printLine(n >> 1);
		} else {
			out.printLine(-1);
		}
	}
}
