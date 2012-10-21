package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger number = in.readBigInteger();
		out.printLine((number.add(BigInteger.ONE)).multiply(number.compareTo(BigInteger.ZERO) > 0 ? number : number.abs().add(BigInteger.valueOf(2))).divide(BigInteger.valueOf(2)));
	}
}
