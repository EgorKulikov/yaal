package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PolynomialPartitionFunction {
	private static final long MOD = (long) (1e9 + 7);
	private static final long MAX = Long.MAX_VALUE - 2 * MOD * MOD;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int partCount = in.readInt();
		int sum = in.readInt();
		long x = in.readInt();
		int power = in.readInt();
		long[] coefficients = IOUtils.readLongArray(in, power + 1);
		long[] values = new long[sum + 1];
		for (int i = 0; i <= sum; i++) {
			long argument = (x * i) % MOD;
			for (int j = power; j >= 0; j--)
				values[i] = (values[i] * argument + coefficients[j]) % MOD;
		}
		long[] answer = power(values, partCount);
		out.printLine(answer[sum]);
	}

	private long[] power(long[] values, int exponent) {
		if (exponent == 1)
			return values;
		long[] result = power(values, exponent / 2);
		result = multiply(result, result);
		if (exponent % 2 == 1)
			result = multiply(result, values);
		return result;
	}

	private long[] multiply(long[] first, long[] second) {
		long[] result = new long[first.length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j <= i; j++) {
				result[i] += first[j] * second[i - j];
				result[i] %= MOD;
			}
		}
		return result;
	}
}
