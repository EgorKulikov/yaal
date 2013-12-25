package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] values = IOUtils.readIntArray(in, 4);
		Rational[] asRational = new Rational[4];
		for (int i = 0; i < 4; i++)
			asRational[i] = new Rational(values[i], 1);
		out.printLine(calculate(asRational) + 21);
    }

	private int calculate(Rational[] values) {
		if (values.length == 1) {
			if (values[0].denominator != 1)
				return Integer.MAX_VALUE / 2;
			return (int) values[0].numerator - 21;
		}
		Rational[] next = new Rational[values.length - 1];
		int result = Integer.MAX_VALUE / 2;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (i == j)
					continue;
				int l = 0;
				for (int k = 0; k < values.length; k++) {
					if (k != i && k != j)
						next[l++] = values[k];
				}
				next[l] = values[i].add(values[j]);
				int candidate = calculate(next);
				if (Math.abs(candidate) < Math.abs(result) || candidate == -result && candidate < 0)
					result = candidate;
				next[l] = values[i].subtract(values[j]);
				candidate = calculate(next);
				if (Math.abs(candidate) < Math.abs(result) || candidate == -result && candidate < 0)
					result = candidate;
				if (i < j) {
					next[l] = values[i].multiply(values[j]);
					candidate = calculate(next);
					if (Math.abs(candidate) < Math.abs(result) || candidate == -result && candidate < 0)
						result = candidate;
				}
				if (values[j].numerator != 0) {
					next[l] = values[i].divide(values[j]);
					candidate = calculate(next);
					if (Math.abs(candidate) < Math.abs(result) || candidate == -result && candidate < 0)
						result = candidate;
				}
			}
		}
		return result;
	}
}
