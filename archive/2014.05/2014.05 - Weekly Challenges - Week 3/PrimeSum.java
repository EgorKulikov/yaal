package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class PrimeSum {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		long count = in.readLong();
		if (n < 2 * count) {
			out.printLine("No");
			return;
		}
		if (count >= 3 || n % 2 == 0 && count >= 2) {
			out.printLine("Yes");
			return;
		}
		if (count == 2 && BigInteger.valueOf(n - 2).isProbablePrime(100)) {
			out.printLine("Yes");
			return;
		}
		if (count == 1 && BigInteger.valueOf(n).isProbablePrime(100)) {
			out.printLine("Yes");
			return;
		}
		out.printLine("No");
    }
}
