package net.egork.numbers;

import net.egork.collections.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class NumberAlgorithms {
	public static long gcd(long a, long b) {
		while (b != 0) {
			long temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}

	public static double fastHypot(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}

	public static int[] generatePrimes(int upTo) {
		boolean[] isPrime = generatePrimalityTable(upTo);
		List<Integer> primes = new ArrayList<Integer>();
		for (int i = 0; i < upTo; i++) {
			if (isPrime[i])
				primes.add(i);
		}
		return CollectionUtils.toArray(primes);
	}

	public static boolean[] generatePrimalityTable(int upTo) {
		boolean[] isPrime = new boolean[upTo];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i * i < upTo; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j < upTo; j += i)
					isPrime[j] = false;
			}
		}
		return isPrime;
	}
}
