package net.egork.numbers.rational;

import net.egork.numbers.NumberAlgorithms;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Rational {
	private final long numerator;
	private final long denominator;

	public Rational(long numerator, long denominator) {
		if (denominator == 0)
			throw new IllegalArgumentException();
		long gcd = NumberAlgorithms.gcd(Math.abs(numerator), Math.abs(denominator));
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
}
