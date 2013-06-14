package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class FunWithDecimalNumberSystem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger number = in.readBigInteger();
		BigInteger delta = BigInteger.ONE;
		BigInteger three = BigInteger.valueOf(3);
		int length = 0;
		while (number.compareTo(delta) >= 0) {
			number = number.subtract(delta);
			delta = delta.multiply(three);
			length++;
		}
		char[] s = number.toString(3).toCharArray();
		for (int i = 0; i < s.length; i++)
			s[i] += 4;
		for (int i = 0; i < length - s.length; i++)
			out.print(4);
		out.printLine(s);
    }
}
