package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BettyAndTheModularExponentiation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int base = in.readInt();
		int power = in.readInt();
		out.printLine(IntegerUtils.power(base, power, 1000000000));
    }
}
