package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskG {
	private int[] primes = IntegerUtils.generatePrimes(1000000);

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int from = in.readInt();
		int to = in.readInt() + 1;
		if (from == -1 && to == 0)
			throw new UnknownError();
		from = Arrays.binarySearch(primes, from);
		if (from < 0)
			from = -from - 1;
		to = Arrays.binarySearch(primes, to);
		if (to < 0)
			to = -to - 1;
		out.println(to - from);
	}
}
