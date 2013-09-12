package net.egork;

import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstSize = in.readInt();
		int secondSize = in.readInt();
		if (firstSize == 0 && secondSize == 0)
			throw new UnknownError();
		int[] f = IOUtils.readIntArray(in, firstSize + secondSize);
		Rational[][] m = new Rational[firstSize + secondSize][firstSize + secondSize + 1];
		ArrayUtils.fill(m, Rational.ZERO);
		m[m.length - 1][m.length] = Rational.ONE;
		for (int i = 0; i < firstSize; i++)
			m[i][i] = new Rational(-1, 1);
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; i + j < m.length && j < secondSize; j++)
				m[i + j][firstSize + j] = new Rational(f[i], 1);
		}
		for (int i = 0; i < m.length; i++) {
			long minSum = Long.MAX_VALUE;
			int row = -1;
			for (int j = i; j < m.length; j++) {
				if (m[j][i].numerator != 0 && Math.abs(m[j][i].numerator) + Math.abs(m[j][i].denominator) < minSum) {
					row = j;
					minSum = Math.abs(m[j][i].numerator) + Math.abs(m[j][i].denominator);
				}
			}
			for (int j = i; j <= m.length; j++) {
				Rational t = m[row][j];
				m[row][j] = m[i][j];
				m[i][j] = t;
			}
			for (int j = m.length; j >= i; j--)
				m[i][j] = m[i][j].divide(m[i][i]);
			for (int j = 0; j < m.length; j++) {
				if (i == j)
					continue;
				for (int k = m.length; k >= i; k--)
					m[j][k] = m[j][k].subtract(m[i][k].multiply(m[j][i]));
			}
		}
		List<Pair<Rational, Integer>> first = new ArrayList<Pair<Rational, Integer>>();
		List<Pair<Rational, Integer>> second = new ArrayList<Pair<Rational, Integer>>();
		for (int i = 0; i < m.length; i++) {
			if (m[i][m.length].numerator != 0) {
				if (i < firstSize)
					first.add(Pair.makePair(m[i][m.length], i));
				else
					second.add(Pair.makePair(m[i][m.length], i - firstSize));
			}
		}
		if (first.isEmpty())
			first.add(Pair.makePair(Rational.ZERO, 0));
		if (second.isEmpty())
			second.add(Pair.makePair(Rational.ZERO, 0));
		if (testNumber != 1)
			out.printLine();
		print(out, first);
		print(out, second);
	}

	private void print(OutputWriter out, List<Pair<Rational, Integer>> first) {
		boolean was = false;
		for (Pair<Rational, Integer> pair : first) {
			if (was)
				out.print(' ');
			else
				was = true;
			out.print('(');
			if (pair.first.denominator == 1)
				out.print(pair.first.numerator);
			else
				out.print(pair.first);
			out.print(',');
			out.print(pair.second);
			out.print(')');
		}
		out.printLine();
	}
}
