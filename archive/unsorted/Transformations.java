package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Transformations {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger n = in.readBigInteger();
		StringBuilder answer = new StringBuilder();
		BigInteger four = BigInteger.valueOf(4);
		while (!n.equals(four)) {
			long lastDigit = n.mod(BigInteger.TEN).longValue();
			if (lastDigit == 0) {
				n = n.divide(BigInteger.TEN);
				answer.append('A');
			} else if (lastDigit == 4) {
				n = n.divide(BigInteger.TEN);
				answer.append('B');
			} else {
				n = n.shiftLeft(1);
				answer.append('C');
			}
		}
		out.printLine(answer.reverse());
	}
}
