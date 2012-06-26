package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	private int[] primes;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt();
		int p = in.readInt();
		if (index == 1) {
			out.printLine(p);
			return;
		}
		int max = 1000000001;
		if ((long)p * p >= max) {
			out.printLine(0);
			return;
		}
		if (p > 1000) {
			int[] primes = IntegerUtils.generatePrimes((max - 1) / p + 1);
			int pIndex = Arrays.binarySearch(primes, p);
			index += pIndex - 2;
			if (index < primes.length)
				out.printLine(p * primes[index]);
			else
				out.printLine(0);
			return;
		}
		int left = 1;
		int right = (max - 1) / p;
		primes = IntegerUtils.generatePrimes(p);
		while (left != right) {
			int middle = (left + right) / 2;
			if (get(middle, 1, 0) < index)
				left = middle + 1;
			else
				right = middle;
		}
		if (get(left, 1, 0) != index)
			out.printLine(0);
		else
			out.printLine(p * left);
	}

	private int get(int upTo, int mu, int startIndex) {
		int result = mu * upTo;
		for (int i = startIndex; i < primes.length && upTo >= primes[i]; i++)
			result += get(upTo / primes[i], -mu, i + 1);
		return result;
	}
}
