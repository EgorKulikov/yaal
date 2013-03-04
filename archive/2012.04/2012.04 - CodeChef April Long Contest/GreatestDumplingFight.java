package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GreatestDumplingFight {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long a = in.readLong();
		long b = in.readLong();
		long c = in.readLong();
		long d = in.readLong();
		long length = in.readLong();
		long firstStep = IntegerUtils.gcd(a, b);
		long secondStep = IntegerUtils.gcd(c, d);
		long gcd = IntegerUtils.gcd(firstStep, secondStep);
		long answer = length / firstStep / (secondStep / gcd) * 2 + 1;
		out.printLine(answer);
	}
}
