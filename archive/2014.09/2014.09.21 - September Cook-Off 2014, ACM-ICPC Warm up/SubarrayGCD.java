package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SubarrayGCD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int g = 0;
		for (int i : array) {
			g = IntegerUtils.gcd(g, i);
		}
		if (g == 1) {
			out.printLine(count);
		} else {
			out.printLine(-1);
		}
    }
}
