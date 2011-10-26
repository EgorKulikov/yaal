package net.egork.numbers;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Rational implements Comparable<Rational> {
	public static final Rational MAX_VALUE = new Rational(Integer.MAX_VALUE, 1);
	public static final Rational MIN_VALUE = new Rational(Integer.MIN_VALUE, 1);
	public static final Rational ONE = new Rational(1, 1);
	public static final Rational ZERO = new Rational(0, 1);

	public final long numerator;
	public final long denominator;

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

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}

	public int compareTo(Rational other) {
		return IntegerUtils.longCompare(numerator * other.denominator, denominator * other.numerator);
	}

	public Rational add(Rational other) {
		return new Rational(numerator * other.denominator + denominator * other.numerator,
			denominator * other.denominator);
	}

	public Rational reverse() {
		if (numerator == 0)
			throw new ArithmeticException();
		return new Rational(denominator, numerator);
	}

	public Rational multiply(long number) {
		return new Rational(numerator * number, denominator);
	}

	public Rational subtract(Rational other) {
		return new Rational(numerator * other.denominator - denominator * other.numerator,
			denominator * other.denominator);
	}
}
