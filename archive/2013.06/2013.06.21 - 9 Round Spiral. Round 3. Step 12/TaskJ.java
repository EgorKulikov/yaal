package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger number = in.readBigInteger();
		out.printLine(number.subtract(BigInteger.ONE));
    }
}
