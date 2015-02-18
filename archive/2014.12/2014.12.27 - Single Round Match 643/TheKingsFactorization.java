package net.egork;

import java.math.BigInteger;
import java.util.Arrays;

public class TheKingsFactorization {
    public long[] getVector(long N, long[] primes) {
		for (int i = 707106781; ; i--) {
			if (BigInteger.valueOf(i).isProbablePrime(100)) {
				System.err.println(i);
				break;
			}
		}
		if (primes.length == 1) {
			if (N == primes[0]) {
				return primes;
			}
			return new long[]{primes[0], N / primes[0]};
		}
		for (long l : primes) {
			N /= l;
		}
		long[] answer = new long[primes.length * 2];
		for (int i = 0; i < primes.length; i++) {
			answer[2 * i] = primes[i];
		}
		for (int i = 0; i < primes.length - 2; i++) {
			for (long j = primes[i]; j <= primes[i + 1]; j++) {
				if (N % j == 0) {
					N /= j;
					answer[2 * i + 1] = j;
					break;
				}
			}
		}
		if (BigInteger.valueOf(N).isProbablePrime(100)) {
			answer[2 * primes.length - 3] = N;
			answer = Arrays.copyOf(answer, answer.length - 1);
		} else {
			for (long j = primes[primes.length - 2]; j <= primes[primes.length - 1]; j++) {
				if (N % j == 0) {
					answer[2 * primes.length - 3] = j;
					answer[2 * primes.length - 1] = N / j;
					break;
				}
			}
		}
		return answer;
    }
}
