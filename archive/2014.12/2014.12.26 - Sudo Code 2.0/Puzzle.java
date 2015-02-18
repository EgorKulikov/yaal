package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Puzzle {
	int[] primes = IntegerUtils.generatePrimes(50000);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		long answer = 1;
		for (int i : primes) {
			int total = 0;
			int copy = number;
			while (copy >= i) {
				total += copy /= i;
			}
			answer *= total + 1;
			answer %= (int)(1e9 + 7);
		}
		out.printLine(answer);
    }
}
