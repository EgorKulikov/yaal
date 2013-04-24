package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class RealMangoesForRanjith {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		BigInteger half = BigInteger.valueOf((count - 1) / 2);
		out.printLine(half.multiply(half).mod(BigInteger.valueOf(count)));
    }
}
