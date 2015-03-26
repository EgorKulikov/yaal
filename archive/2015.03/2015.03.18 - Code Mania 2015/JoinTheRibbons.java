package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JoinTheRibbons {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long gcd = 0;
		for (int i = 0; i < count; i++) {
			gcd = IntegerUtils.gcd(gcd, in.readLong());
		}
		out.printLine(gcd);
	}
}
