package net.egork;

import java.util.Arrays;

public class PenguinEmperor {
	private static final long MOD = (long) (1e9 + 7);

	public int countJourneys(int count, long daysPassed) {
		long[] ways = new long[count];
		ways[0] = 1;
		long[] next = new long[count];
		if (daysPassed >= count) {
			for (int k = 1; k < count; k++) {
				Arrays.fill(next, 0);
				int nCity = k;
				int pCity = count - k;
				for (int j = 0; j < count; j++) {
					next[nCity] += ways[j];
					if (2 * k != count)
						next[pCity] += ways[j];
					nCity++;
					if (nCity == count)
						nCity = 0;
					pCity++;
					if (pCity == count)
						pCity = 0;
				}
				long[] temp = ways;
				ways = next;
				next = temp;
				for (int i = 0; i < count; i++)
					ways[i] %= MOD;
			}
			long[] result = new long[count];
			power(ways, result, new long[count], daysPassed / count);
			ways = result;
			daysPassed %= count;
		}
		for (int k = 1; k <= daysPassed; k++) {
			Arrays.fill(next, 0);
			int nCity = k;
			int pCity = count - k;
			for (int j = 0; j < count; j++) {
				next[nCity] += ways[j];
				if (2 * k != count)
					next[pCity] += ways[j];
				nCity++;
				if (nCity == count)
					nCity = 0;
				pCity++;
				if (pCity == count)
					pCity = 0;
			}
			long[] temp = ways;
			ways = next;
			next = temp;
			for (int i = 0; i < count; i++)
				ways[i] %= MOD;
		}
		return (int) (ways[0] % MOD);
	}

	private void power(long[] ways, long[] result, long[] temp, long exponent) {
		if (exponent == 0) {
			result[0] = 1;
			return;
		}
		if ((exponent & 1) == 0) {
			power(ways, temp, result, exponent >> 1);
			multiply(result, temp, temp);
		} else {
			power(ways, temp, result, exponent - 1);
			multiply(result, temp, ways);
		}
	}

	private void multiply(long[] a, long[] b, long[] c) {
		Arrays.fill(a, 0);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++)
				a[(i + j) % a.length] += b[i] * c[j] % MOD;
		}
		for (int i = 0; i < a.length; i++)
			a[i] %= MOD;
	}
}
