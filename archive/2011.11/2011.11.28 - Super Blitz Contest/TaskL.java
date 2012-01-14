package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskL {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long number = in.readLong();
		for (long i = 2; i * i < number; i++) {
			if (number % i == 0) {
				if (BigInteger.valueOf(number / i).isProbablePrime(100))
					out.printLine("YES");
				else
					out.printLine("NO");
				return;
			}
		}
		out.printLine("NO");
	}
}
