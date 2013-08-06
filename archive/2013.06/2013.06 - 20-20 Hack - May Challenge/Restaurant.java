package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Restaurant {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int breadth = in.readInt();
		int g = IntegerUtils.gcd(length, breadth);
		out.printLine(length * breadth / (g * g));
    }
}
