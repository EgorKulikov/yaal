package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigInteger;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(in.readBigInteger().mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO) ? "YES" : "NO");
	}
}
