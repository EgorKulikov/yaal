package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class GoldenTrees {
	private static final long MOD = (long) (1e8 + 7);
	int size;

	void power(long[] base, long[] result, long[] temp, long exponent) {
		if (exponent == 1) {
			System.arraycopy(base, 0, result, 0, base.length);
			return;
		}
		if ((exponent & 1) == 1) {
			power(base, result, temp, exponent >> 1);
			multiply(temp, result, result);
			multiply(result, temp, base);
		} else {
			power(base, temp, result, exponent >> 1);
			multiply(result, temp, temp);
		}
	}

	private void multiply(long[] result, long[] a, long[] b) {
		Arrays.fill(result, 0);
		for (int i = 0; i < size; i++) {
			int iShift = i * size;
			for (int j = 0; j < size; j++) {
				int jShift = j * size;
				for (int k = 0; k < size; k++)
					result[iShift + k] += a[iShift + j] * b[jShift + k];
			}
		}
		for (int i = 0; i < result.length; i++)
			result[i] %= MOD - 1;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long init = in.readInt();
		int first = in.readInt();
		int second = in.readInt();
		int last = in.readInt();
		int year = in.readInt();
		long[] answer = new long[2 + first + second];
		answer[1] = init;
		for (int i = 2; i < 2 + first; i++)
			answer[i] = answer[i - 1] + 1;
		for (int i = 2 + first; i < 2 + first + second; i++)
			answer[i] = (answer[i - 1] * 2) % MOD;
		if (year < answer.length) {
			out.printLine(answer[year]);
			return;
		}
/*		Matrix matrix = new Matrix(last, last);
		Matrix.mod = MOD - 1;
		Arrays.fill(matrix.data[0], 1);
		for (int i = 1; i < last; i++)
			matrix.data[i][i - 1] = 1;
		matrix = matrix.fastPower(year - answer.length + 1);
		long result = 1;
		for (int i = 0; i < last; i++)
			result = result * IntegerUtils.power(answer[answer.length - 1 - i], matrix.data[0][i], MOD) % MOD;*/
		long[] base = new long[last * last];
		Arrays.fill(base, 0, last, 1);
		for (int i = 1; i < last; i++)
			base[i * last + i - 1] = 1;
		long[] power = new long[last * last];
		size = last;
		power(base, power, new long[last * last], year - answer.length + 1);
		long result = 1;
		for (int i = 0; i < last; i++)
			result = result * IntegerUtils.power(answer[answer.length - 1 - i], power[i], MOD) % MOD;
		out.printLine(result);
	}
}
