package net.egork.numbers;

import net.egork.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class IntegerUtils {
	public static long gcd(long a, long b) {
		while (b != 0) {
			long temp = a % b;
			a = b;
			b = temp;
		}
		return a;
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

	public static long powerInFactorial(long n, long p) {
		long result = 0;
		while (n != 0) {
			result += n /= p;
		}
		return result;
	}

	public static int sumDigits(CharSequence number) {
		int result = 0;
		for (int i = number.length() - 1; i >= 0; i--)
			result += digitValue(number.charAt(i));
		return result;
	}

	public static int digitValue(char digit) {
		if (Character.isDigit(digit))
			return digit - '0';
		if (Character.isUpperCase(digit))
			return digit + 10 - 'A';
		return digit + 10 - 'a';
	}

	public static long calculateSumDivisors(long n) {
		long result = 1;
		for (long i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				long pn = i;
				do {
					pn *= i;
					n /= i;
				} while (n % i == 0);
				result *= (pn - 1) / (i - 1);
			}
		}
		if (n != 1)
			result *= n + 1;
		return result;
	}

	public static int longCompare(long a, long b) {
		if (a < b)
			return -1;
		if (a > b)
			return 1;
		return 0;
	}

	public static long[][] generateBinomialCoefficients(int n) {
		long[][] result = new long[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			result[i][0] = 1;
			for (int j = 1; j <= i; j++)
				result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
		}
		return result;
	}

	public static int calculateNumDivisors(long n) {
		int result = 1;
		for (long i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				int power = 1;
				do {
					power++;
					n /= i;
				} while (n % i == 0);
				result *= power;
			}
		}
		if (n != 1)
			result *= 2;
		return result;
	}
}
