package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class TaskC {
	private double[] probabilities;
	private double[] expected;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int length = in.readInt();
		int[] a = IOUtils.readIntArray(in, length);
		int[] b = IOUtils.readIntArray(in, length);
		int[] c = IOUtils.readIntArray(in, length);
		BigInteger[][] count = new BigInteger[length + 1][11];
		Arrays.fill(count[0], BigInteger.ZERO);
		count[0][1] = BigInteger.ONE;
		for (int i = 0; i < length; i++) {
			Arrays.fill(count[i + 1], BigInteger.ZERO);
			for (int j = 0; j < 10; j++) {
				int value = (a[i] * j * j + b[i] * j + c[i]) % 11;
				for (int k = 0; k <= 10; k++)
					count[i + 1][(k * value) % 11] = count[i + 1][(k * value) % 11].add(count[i][k]);
			}
		}
		count[length][0] = count[length][0].add(count[length][10]);
		BigInteger sum = BigInteger.ZERO;
		for (int i = 0; i < 10; i++) {
			if (count[length][i].equals(BigInteger.ZERO)) {
				out.println(-1);
				return;
			}
			sum = sum.add(count[length][i]);
		}
		probabilities = new double[10];
		for (int i = 0; i < 10; i++)
			probabilities[i] = count[length][i].doubleValue() / sum.doubleValue();
		expected = new double[1 << 10];
		Arrays.fill(expected, -1);
		expected[1023] = 0;
		out.printf("%.9f\n", go(0));
	}

	private double go(int mask) {
		if (expected[mask] != -1)
			return expected[mask];
		expected[mask] = 0;
		double returnProbability = 0;
		for (int i = 0; i < 10; i++) {
			if ((mask >> i & 1) == 0)
				expected[mask] += probabilities[i] * go(mask + (1 << i));
			else
				returnProbability += probabilities[i];
		}
		expected[mask]++;
		expected[mask] /= 1 - returnProbability;
		return expected[mask];
	}
}
