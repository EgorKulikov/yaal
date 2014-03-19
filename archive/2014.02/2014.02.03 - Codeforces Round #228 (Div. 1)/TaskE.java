package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.map.CPPMap;
import net.egork.collections.map.Counter;
import net.egork.collections.set.EHashSet;
import net.egork.misc.Factory;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Rational[] x = new Rational[count];
		Rational[] y = new Rational[count];
		Rational[] dx = new Rational[count];
		Rational[] dy = new Rational[count];
		for (int i = 0; i < count; i++) {
			int t0 = in.readInt();
			int x0 = in.readInt();
			int y0 = in.readInt();
			int t1 = in.readInt();
			int x1 = in.readInt();
			int y1 = in.readInt();
			dx[i] = new Rational(x1 - x0, t1 - t0);
			dy[i] = new Rational(y1 - y0, t1 - t0);
			x[i] = new Rational(x0, 1).subtract(new Rational(t0, 1).multiply(dx[i]));
			y[i] = new Rational(y0, 1).subtract(new Rational(t0, 1).multiply(dy[i]));
		}
		Rational[] cx = new Rational[count];
		Rational[] cy = new Rational[count];
		Rational[] cdx = new Rational[count];
		Rational[] cdy = new Rational[count];
		long answer = 1;
		for (int i = 0; i < count; i++) {
			IntList goodList = new IntArrayList();
			for (int j = i + 1; j < count; j++) {
				if (dx[j].equals(dx[i]) && dy[j].equals(dy[i]))
					continue;
				cx[j] = x[j].subtract(x[i]);
				cy[j] = y[j].subtract(y[i]);
				cdx[j] = dx[j].subtract(dx[i]);
				cdy[j] = dy[j].subtract(dy[i]);
				if (cx[j].numerator.multiply(cdy[j].numerator).multiply(cy[j].denominator).multiply(cdx[j].denominator).equals(
					cy[j].numerator.multiply(cdx[j].numerator).multiply(cx[j].denominator).multiply(cdy[j].denominator)))
				{
					goodList.add(j);
				}
			}
			int[] good = goodList.toArray();
			Counter<Rational> times = new Counter<Rational>();
			Map<Rational, Set<Rational>> lines = new CPPMap<Rational, Set<Rational>>(new Factory<Set<Rational>>() {
				public Set<Rational> create() {
					return new EHashSet<Rational>();
				}
			});
			Set<Rational> special = new EHashSet<Rational>();
			for (int j : good) {
				if (cdy[j].equals(Rational.ZERO)) {
					times.add(cx[j].divide(cdx[j]));
					special.add(cdx[j]);
				} else {
					times.add(cy[j].divide(cdy[j]));
					lines.get(cdx[j].divide(cdy[j])).add(cdy[j]);
				}
			}
			for (long value : times.values())
				answer = Math.max(answer, value + 1);
			for (Set<Rational> set : lines.values())
				answer = Math.max(answer, set.size() + 1);
			answer = Math.max(answer, special.size() + 1);
		}
		out.printLine(answer);
    }

	public static class Rational implements Comparable<Rational> {
		public static final Rational ONE = new Rational(1, 1);
		public static final Rational ZERO = new Rational(0, 1);

		public final BigInteger numerator;
		public final BigInteger denominator;

		public Rational(long numerator, long denominator) {
			if (denominator == 0)
				throw new IllegalArgumentException();
			long gcd = IntegerUtils.gcd(Math.abs(numerator), Math.abs(denominator));
			if (denominator > 0) {
				this.numerator = BigInteger.valueOf(numerator / gcd);
				this.denominator = BigInteger.valueOf(denominator / gcd);
			} else {
				this.numerator = BigInteger.valueOf(-numerator / gcd);
				this.denominator = BigInteger.valueOf(-denominator / gcd);
			}
		}

		public Rational(BigInteger numerator, BigInteger denominator) {
			if (denominator.equals(BigInteger.ZERO))
				throw new IllegalArgumentException();
			BigInteger gcd = numerator.abs().gcd(denominator.abs());
			if (denominator.compareTo(BigInteger.ZERO) > 0) {
				this.numerator = numerator.divide(gcd);
				this.denominator = denominator.divide(gcd);
			} else {
				this.numerator = numerator.negate().divide(gcd);
				this.denominator = denominator.negate().divide(gcd);
			}
		}

		@Override
		public String toString() {
			return numerator + "/" + denominator;
		}

		public int compareTo(Rational other) {
			return numerator.multiply(other.denominator).compareTo(denominator.multiply(other.numerator));
		}

		public Rational add(Rational other) {
			return new Rational(numerator.multiply(other.denominator).add(denominator.multiply(other.numerator)),
				denominator.multiply(other.denominator));
		}

		public Rational reverse() {
			return new Rational(denominator, numerator);
		}

		public Rational subtract(Rational other) {
			return new Rational(numerator.multiply(other.denominator).subtract(denominator.multiply(other.numerator)),
				denominator.multiply(other.denominator));
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Rational rational = (Rational) o;

			if (!denominator.equals(rational.denominator)) return false;
			if (!numerator.equals(rational.numerator)) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = numerator.hashCode();
			result = 31 * result + denominator.hashCode();
			return result;
		}

		public Rational divide(Rational other) {
			return new Rational(numerator.multiply(other.denominator), other.numerator.multiply(denominator));
		}

		public Rational multiply(Rational other) {
			return new Rational(numerator.multiply(other.numerator), other.denominator.multiply(denominator));
		}
	}

}
