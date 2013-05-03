package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger radius = in.readBigInteger();
		BigInteger paint = in.readBigInteger();
		BigInteger left = BigInteger.ONE;
		BigInteger right = paint.divide(radius.shiftLeft(1));
		while (left.compareTo(right) < 0) {
			BigInteger middle = left.add(right).add(BigInteger.ONE).shiftRight(1);
			BigInteger required = BigInteger.valueOf(2).multiply(radius).multiply(middle).add(middle.shiftLeft(1).subtract(BigInteger.valueOf(1)).multiply(middle));
			if (required.compareTo(paint) <= 0)
				left = middle;
			else
				right = middle.subtract(BigInteger.ONE);
		}
		out.printLine("Case #" + testNumber + ":", left);
    }
}
