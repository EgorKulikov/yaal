package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class AsmanyNumberVerification {
	Set<BigInteger> asmany = new HashSet<BigInteger>();

	{
		BigInteger current = BigInteger.valueOf(2);
		BigInteger max = BigInteger.TEN.pow(1000);
		for (int i = 2; current.compareTo(max) < 0; i++) {
			asmany.add(current);
			current = current.multiply(BigInteger.valueOf(i));
			current = current.divide(BigInteger.valueOf((i + 1) / 2));
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(asmany.contains(in.readBigInteger()) ? "YES" : "NO");
	}
}
