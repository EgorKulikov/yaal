package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int gcd = in.readInt();
		out.printLine(gcd * (6 * count - 1));
		for (int i = 0; i < count; i++) {
			out.printLine(gcd * (6 * i + 1), gcd * (6 * i + 2), gcd * (6 * i + 3), gcd * (6 * i + 5));
		}
    }
}
