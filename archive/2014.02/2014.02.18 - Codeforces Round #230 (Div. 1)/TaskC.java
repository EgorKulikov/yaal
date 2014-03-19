package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long upTo = in.readLong();
		int order = in.readInt();
		long[][] c = IntegerUtils.generateBinomialCoefficients(order + 1, MOD);
		long[][] matrix = new long[2 * order + 2][2 * order + 2];
		for (int i = 0; i <= order; i++) {
			for (int j = 0; j <= i; j++) {
				matrix[i][j] = c[i][j];
				for (int k = 0; k <= j; k++) {
					matrix[i][k + order + 1] += c[i][j] * c[j][k] % MOD;// * ((j - k) % 2 == 0 ? 1 : -1);
					matrix[i][k + order + 1] %= MOD;
					matrix[i][k + order + 1] += MOD;
					matrix[i][k + order + 1] %= MOD;
				}
			}
			matrix[i + order + 1][i] = 1;
		}
		long[] plain = Matrix.convert(matrix);
		long[] resultPlain = sumPowers(plain, upTo - 1, MOD, 2 * order + 2);
		long[][] result = Matrix.convert(resultPlain, 2 * order + 2);
		long[] vector = new long[2 * order + 2];
		Arrays.fill(vector, 0, order + 2, 1);
		long answer = 0;
		for (int i = 0; i < 2 * order + 2; i++) {
			answer += result[order][i] * vector[i];
		}
		answer %= MOD;
		out.printLine(answer);
    }

	public static long[] sumPowers(long[] matrix, long exponent, long mod, int side) {
		long[] result = new long[matrix.length];
		long[] power = new long[matrix.length];
		long[] temp = new long[matrix.length];
		long[] temp2 = new long[matrix.length];
		sumPowers(matrix, result, power, temp, temp2, exponent + 1, mod, side);
		return result;
	}

	private static void sumPowers(long[] matrix, long[] result, long[] power, long[] temp, long[] temp2, long exponent, long mod, int side) {
		if (exponent == 0) {
			for (int i = 0; i < matrix.length; i += side + 1)
				power[i] = 1 % mod;
			return;
		}
		if ((exponent & 1) == 0) {
			sumPowers(matrix, result, temp, power, temp2, exponent >> 1, mod, side);
			multiply(temp2, result, temp, mod, side);
			add(result, temp2, mod, side);
			multiply(power, temp, temp, mod, side);
		} else {
			sumPowers(matrix, result, temp, power, temp2, exponent - 1, mod, side);
			add(result, temp, mod, side);
			multiply(power, temp, matrix, mod, side);
		}
	}

	public static void multiply(long[] c, long[] a, long[] b, long mod, int side) {
		Arrays.fill(c, 0);
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				for (int k = 0; k < side; k++) {
					c[i * side + k] += a[i * side + j] * b[j * side + k];
					if ((j & 7) == 0)
						c[i * side + k] %= mod;
				}
			}
		}
		for (int i = 0; i < c.length; i++)
			c[i] %= mod;
	}

	public static void add(long[] c, long[] a, long mod, int side) {
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				c[i * side + j] += a[i * side + j];
				if (c[i * side + j] >= mod)
					c[i * side + j] -= mod;
			}
		}
	}


}
