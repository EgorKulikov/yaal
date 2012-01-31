package net.egork;

import java.math.BigInteger;

public class MinskyMystery {
	private static final long MOD = 1000000009;

	public int computeAnswer(long N) {
		if (N <= 1)
			return -1;
		for (long d = 2; d * d <= N; d++) {
			if (N % d == 0) {
				long answer = getAnswer(N, d);
				return (int) answer;
			}
		}
		return (int) getAnswer(N, N);
	}

	private long getAnswer(long N, long d) {
		long answer = (d - 2) % MOD * (N % MOD) % MOD * 4 % MOD + d + 3 * N;
		answer %= MOD;
		if (N != d) {
			for (long i = 2; i <= d; i++)
				answer += (N + i - 1) / i;
			answer %= MOD;
			return answer;
		}
		answer++;
		long maxI = 1;
		for (long i = 2; i * i < N; i++) {
			maxI = i;
			answer += (N + i - 1) / i;
		}
		for (long x = 2; (x - 1) * (x - 1) < N; x++)
			answer += x * ((N + x - 2) / (x - 1) - Math.max((N + x - 1) / x, maxI + 1)) % MOD;
		return answer % MOD;
	}

	public static void main(String[] args) {
		System.out.println(BigInteger.valueOf(999999999989L).isProbablePrime(100));
	}
}

