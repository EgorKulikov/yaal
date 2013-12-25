package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long product = in.readLong();
		int[] primes = IntegerUtils.generatePrimes(1000000);
		for (int i = 1; i < primes.length; i++) {
			if ((long)primes[i - 1] * primes[i] == product) {
				out.printLine(primes[i - 1], primes[i]);
				return;
			}
		}
    }
}
