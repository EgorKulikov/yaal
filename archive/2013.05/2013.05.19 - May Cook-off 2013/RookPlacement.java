package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RookPlacement {
	static final int MOD = 10007;
	long[] factorial = IntegerUtils.generateFactorial(MOD, MOD);
	long[] reverseFactorial = IntegerUtils.generateReverseFactorials(MOD, MOD);

	static {
		long[][][] current = new long[10][10][10];
		long[][][] valid = new long[10][10][10];
		ArrayUtils.fill(current[0], 1);
		ArrayUtils.fill(valid[0], 1);
		for (int i = 1; i < 10; i++) {
			current[i][i][i] = current[i - 1][i][i] + current[i - 1][i - 1][i] + current[i - 1][i][i - 1] + current[i - 1][i - 1][i - 1];
			for (int j = i + 1; j < 10; j++)
				current[i][i][j] = current[i][j][i] = current[i][i][j - 1] + current[i - 1][i - 1][j] + current[i - 1][i][j];
			for (int j = i + 1; j < 10; j++) {
				for (int k = i + 1; k < 10; k++) {
					current[i][j][k] = current[i][j - 1][k] + current[i][j][k - 1] - current[i][j - 1][k - 1] + current[i - 1][j][k];
				}
			}
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					valid[i][j][k] = valid[i - 1][j][k];
					if (j != 0)
						valid[i][j][k] += valid[i][j - 1][k];
					if (k != 0)
						valid[i][j][k] += valid[i][j][k - 1];
					if (j != 0 && k != 0)
						valid[i][j][k] -= valid[i][j - 1][k - 1];
				}
			}
			long x = Math.round(Math.sqrt(current[i][i][i]));
//			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++)
					System.err.print((current[i][i][k] / x) + " ");
				System.err.println();
//			}
//			System.err.println();
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long rowCount = in.readLong();
		long columnCount = in.readLong();
		long count = in.readLong();
		a = 1;
		b = 0;
		factorial(rowCount + count - 1);
		reverseFactorial(count);
		reverseFactorial(rowCount - 1);
		factorial(columnCount + count - 1);
		reverseFactorial(count);
		reverseFactorial(columnCount - 1);
		long valid = a;
		if (b != 0)
			valid = 0;
		long lose = 0;
		if (rowCount >= count && columnCount >= count) {
			a = 1;
			b = 0;
			factorial(rowCount + count);
			reverseFactorial(count);
			reverseFactorial(rowCount + 1);
			a *= rowCount - count + 1;
			a %= MOD;
			factorial(columnCount + count);
			reverseFactorial(count);
			reverseFactorial(columnCount + 1);
			a *= columnCount - count + 1;
			a %= MOD;
			lose = a;
			if (b != 0)
				lose = 0;
		}
		valid -= lose;
		valid %= MOD;
		valid += MOD;
		valid %= MOD;
		out.printLine(valid);
	}

	long a;
	int b;

	void factorial(long n) {
		if (n == 0)
			return;
		long x = n / MOD;
		factorial(x);
		long result = factorial[((int) (n % MOD))];
		if ((x & 1) == 1)
			result = (MOD - result) % MOD;
		a *= result;
		a %= MOD;
		b += x;
	}

	void reverseFactorial(long n) {
		if (n == 0)
			return;
		long x = n / MOD;
		reverseFactorial(x);
		long result = reverseFactorial[((int) (n % MOD))];
		if ((x & 1) == 1)
			result = (MOD - result) % MOD;
		a *= result;
		a %= MOD;
		b -= x;
	}

}
