package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int radius = in.readInt();
		long total = 0;
		long one = 0;
		long twoOrMore = 0;
		long qty = 0;
		for (int i = 0; i < count; i++) {
			long d1 = i;
			long d2 = count - 1 - i;
			one += Math.min(d1, 1);
			one += Math.min(d2, 1);
			twoOrMore += Math.max(0, d1 - 1);
			twoOrMore += Math.max(0, d2 - 1);
			total += d1 * (d1 + 1) / 2;
			total += d2 * (d2 + 1) / 2;
			qty += count;
		}
		out.printLine((total * 2 - 2 * twoOrMore + 2 * count + Math.sqrt(2) * (one + 2 * twoOrMore)) / qty * radius);
    }
}
