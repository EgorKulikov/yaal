package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ReversePrimes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int upTo = 400000000;
		int[] isPrime = new int[(upTo + 31) >> 6];
		int limit = 100000000;
//		int odd = 0;
//		for (int i = 1; i < 32; i += 2)
//			odd += 1 << i;
		Arrays.fill(isPrime, -1);
//		isPrime[0] -= 3;
		for (int i = 3; i * i < upTo; i += 2) {
			if ((isPrime[i >> 6] >> ((i & 63) >> 1) & 1) == 0)
				continue;
			for (int j = i * i; j < upTo; j += 2 * i)
				isPrime[j >> 6] &= -1 - (1 << ((j & 63) >> 1));
		}
//		System.err.println("Hi!");
		int qty = 0;
		for (int i = 0; i < upTo >> 7; i++) {
			if (isPrime[i] == 0)
				continue;
			int x = i << 6;
			for (int j = 0; j < 32; j++) {
				if ((isPrime[i] >> j & 1) == 0)
					continue;
				int number = x + 2 * j + 1;
				if (number >= limit && number % 10 >= 4)
					continue;
				int reverse = reverse(number);
				if (reverse > number && (isPrime[reverse >> 6] >> ((reverse & 63) >> 1) & 1) == 1) {
					out.printLine(number);
					qty++;
				}
			}
		}
	}

	private int reverse(int i) {
		int result = 0;
		while (i > 0) {
			result *= 10;
			result += i % 10;
			i /= 10;
		}
		return result;
	}
}
