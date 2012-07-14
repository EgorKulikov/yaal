package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskF {
	private long[][] result;

	{
		int[] primes = IntegerUtils.generatePrimes(1000);
		int[][] count = new int[1001][primes.length];
		for (int i = 2; i <= 1000; i++) {
			int current = i;
			for (int j = 0; j < primes.length; j++) {
				while (current % primes[j] == 0) {
					count[i][j]++;
					current /= primes[j];
				}
			}
		}
		int[][][] totalCount = new int[21][1001][primes.length];
		for (int i = 1; i <= 20; i++) {
			for (int j = 2; j <= 1000; j++) {
				if (j >= i) {
					for (int k = 0; k < primes.length; k++)
						totalCount[i][j][k] = count[j][k] + totalCount[i][j - i][k];
				} else
					System.arraycopy(count[j], 0, totalCount[i][j], 0, primes.length);
			}
		}
		long max = 1000000000000000000L;
		result = new long[21][1001];
		for (int i = 1; i <= 20; i++) {
			for (int j = 1; j <= 1000; j++) {
				result[i][j] = 1;
				for (int k : totalCount[i][j]) {
					if (max / (k + 1) < result[i][j]) {
						result[i][j] = -1;
						break;
					}
					result[i][j] *= k + 1;
				}
			}
		}
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String factorial = in.readString();
		int index = factorial.indexOf('!');
		int n = Integer.parseInt(factorial.substring(0, index));
		long answer = result[factorial.length() - index][n];
		if (answer != -1)
			out.println("Sample " + testNumber + ": " + answer);
		else
			out.println("Sample " + testNumber + ": oo");
	}
}
