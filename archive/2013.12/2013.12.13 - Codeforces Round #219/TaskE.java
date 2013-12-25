package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Rational[] x = new Rational[count];
		Rational[] y = new Rational[count];
		for (int i = 0; i < count; i++) {
			int a = in.readInt();
			int b = in.readInt();
			x[i] = new Rational(a, b);
			int c = in.readInt();
			int d = in.readInt();
			y[i] = new Rational(c, d);
		}
		for (int i = 0; i < count; i++) {
			Rational c = x[i].multiply(x[i]).add(y[i].multiply(y[i]));
			x[i] = x[i].divide(c);
			y[i] = y[i].divide(c);
		}
		final Rational[] xx = new Rational[count * (count - 1) / 2];
		final Rational[] yy = new Rational[count * (count - 1) / 2];
		final long[] aa = new long[count * (count - 1) / 2];
		final long[] bb = new long[count * (count - 1) / 2];
		int k = 0;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				Rational dx = x[i].subtract(x[j]);
				Rational dy = y[i].subtract(y[j]);
				long a = dx.numerator * dy.denominator;
				long b = dx.denominator * dy.numerator;
				long g = IntegerUtils.gcd(a, b);
				a /= g;
				b /= g;
				if (a < 0) {
					a = -a;
					b = -b;
				} else if (a == 0 && b < 0)
					b = -b;
				xx[k] = x[i].add(x[j]);
				yy[k] = y[i].add(y[j]);
				aa[k] = a;
				bb[k++] = b;
			}
		}
		long answer = 0;
		int[] order = ArrayUtils.createOrder(aa.length);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				int value = xx[first].compareTo(xx[second]);
				if (value != 0)
					return value;
				value = yy[first].compareTo(yy[second]);
				if (value != 0)
					return value;
				value = Long.compare(aa[first], aa[second]);
				if (value != 0)
					return value;
				return Long.compare(bb[first], bb[second]);
			}
		});
		int start = 0;
		for (int i = 1; i < aa.length; i++) {
			if (!xx[order[i]].equals(xx[order[i - 1]]) || !yy[order[i]].equals(yy[order[i - 1]])) {
				answer += process(aa, bb, start, i, order);
				start = i;
			}
		}
		answer += process(aa, bb, start, aa.length, order);
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		out.printLine(answer);
    }

	private long process(long[] aa, long[] bb, int from, int to, int[] order) {
		long value = 1;
		long total = 0;
		int start = from;
		for (int i = from + 1; i < to; i++) {
			if (aa[order[i]] != aa[order[i - 1]] || bb[order[i]] != bb[order[i - 1]]) {
				int delta = i - start;
				total += delta;
				value *= delta + 1;
				value %= MOD;
				start = i;
			}
		}
		int delta = to - start;
		total += delta;
		value *= delta + 1;
		value %= MOD;
		return value - total - 1;
	}
}
