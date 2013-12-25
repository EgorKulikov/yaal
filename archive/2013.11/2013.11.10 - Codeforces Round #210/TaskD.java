package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int baseCount = in.readInt();
		int exponentCount = in.readInt();
		int prime = in.readInt();
		int[] bases = IOUtils.readIntArray(in, baseCount);
		int[] exponents = IOUtils.readIntArray(in, exponentCount);
		int exponent = prime - 1;
		for (int i : exponents)
			exponent = IntegerUtils.gcd(exponent, i);
		List<Long> divisors = IntegerUtils.getDivisors(prime - 1);
		Collections.sort(divisors);
		long[] reverse = new long[divisors.size()];
		for (int i = 0; i < reverse.length; i++)
			reverse[i] = (prime - 1) / divisors.get(i);
		boolean[] include = new boolean[divisors.size()];
		for (int i : bases) {
			long base = IntegerUtils.power(i, exponent, prime);
			for (int i1 = divisors.size() - 1; i1 >= 0; i1--) {
				if (IntegerUtils.power(base, reverse[i1], prime) == 1) {
					include[i1] = true;
					break;
				}
			}
		}
		for (int i = 0; i < include.length; i++) {
			if (!include[i])
				continue;
			for (int j = i + 1; j < include.length; j++) {
				if (divisors.get(j) % divisors.get(i) == 0)
					include[j] = true;
			}
		}
		long answer = 0;
		long[] count = new long[divisors.size()];
		for (int i = count.length - 1; i >= 0; i--) {
			count[i] = (prime - 1) / divisors.get(i);
			for (int j = i + 1; j < count.length; j++) {
				if (divisors.get(j) % divisors.get(i) == 0)
					count[i] -= count[j];
			}
			if (include[i])
				answer += count[i];
		}
		out.printLine(answer);
	}
}
