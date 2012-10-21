package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class GCD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int bound = in.readInt();
		boolean[] isPrime = new boolean[bound + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i * i <= bound; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= bound; j += i)
					isPrime[j] = false;
			}
		}
		int primeCount = 0;
		for (boolean b : isPrime) {
			if (b)
				primeCount++;
		}
		int[] primes = new int[primeCount];
		int index = 0;
		for (int i = 0; i <= bound; i++) {
			if (isPrime[i])
				primes[index++] = i;
		}
		int left = 0;
		int right = primeCount - 1;
		int answer = 0;
		while (left <= right) {
			if (primes[left] * primes[right] <= bound)
				left++;
			right--;
			answer++;
		}
		out.printLine(answer);
	}
}
