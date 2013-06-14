package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Resistance {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int f0 = 1;
		int f1 = 1;
		for (int i = 2; i < 2 * n; i++) {
			int f2 = (f0 + f1) % m;
			f0 = f1;
			f1 = f2;
		}
		out.printLine(f0 + "/" + f1);
//		BigInteger factorial = BigInteger.ONE;
//		for (int i = 2; i <= n; i++)
//			factorial = factorial.multiply(BigInteger.valueOf(i));
//		BigInteger sum = BigInteger.ZERO;
//		for (int i = 1; i <= n; i++)
//			sum = sum.add(factorial.divide(BigInteger.valueOf(i)));
//		BigInteger gcd = sum.gcd(factorial);
//		factorial = factorial.divide(gcd);
//		sum = sum.divide(gcd);
//		out.printLine(factorial.mod(BigInteger.valueOf(m)) + "/" + sum.mod(BigInteger.valueOf(m)));
	}
}
