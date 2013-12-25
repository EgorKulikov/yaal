package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Problem11 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		out.printLine(BigInteger.valueOf(a).pow(b).subtract(BigInteger.valueOf(b).pow(a)));
    }
}
