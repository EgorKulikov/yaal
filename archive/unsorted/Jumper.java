package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Jumper {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger n = in.readBigInteger();
		int res = 0;
		if (!n.testBit(0)) {
			n = n.subtract(BigInteger.ONE);
			res = 2;
		}
		res += 2;
		BigInteger m = BigInteger.ONE;
		while (m.compareTo(n) < 0) {
			res += 2;
			m = m.multiply(BigInteger.valueOf(2)).add(BigInteger.ONE);
//			System.err.println(m);
		}
		res++;
		out.printLine(res);
	}
}
