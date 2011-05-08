package net.egork.numbers;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Rational implements Comparable<Rational> {
	private final long numerator;
	private final long denominator;
	public static final Rational MAX_VALUE = new Rational(Integer.MAX_VALUE, 1);
	public static final Rational MIN_VALUE = new Rational(Integer.MIN_VALUE, 1);;

	public Rational(long numerator, long denominator) {
		if (denominator == 0)
			throw new IllegalArgumentException();
		long gcd = IntegerUtils.gcd(Math.abs(numerator), Math.abs(denominator));
		if (denominator > 0) {
			this.numerator = numerator / gcd;
			this.denominator = denominator / gcd;
		} else {
			this.numerator = -numerator / gcd;
			this.denominator = -denominator / gcd;
		}
	}

	public long getNumerator() {
		return numerator;
	}

	public long getDenominator() {
		return denominator;
	}

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}

	public int compareTo(Rational other) {
		return IntegerUtils.longCompare(numerator * other.denominator, denominator * other.numerator);
	}
}
