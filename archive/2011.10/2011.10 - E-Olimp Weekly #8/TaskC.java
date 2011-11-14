package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		long a = Integer.parseInt("0" + in.readString().replace("x", ""));
		in.readCharacter();
		long b = Integer.parseInt("0" + in.readString().replace("y", ""));
		in.readCharacter();
		long c = in.readInt();
		if (c < 0) {
			out.printLine(0);
			return;
		}
		if (a == 0)
			a = 1;
		if (b == 0)
			b = 1;
		if (a * b <= c) {
			if (c % IntegerUtils.gcd(a, b) == 0)
				out.printLine(1);
			else
				out.printLine(0);
			return;
		}
		long min = Math.min(a, b);
		long max = Math.max(a, b);
		long upTo = c / max;
		for (int i = 0; i <= upTo; i++) {
			if ((c - i * max) % min == 0) {
				out.printLine(1);
				return;
			}
		}
		out.printLine(0);
	}
}
