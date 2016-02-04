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
        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
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
        //		return BigInteger.valueOf(numerator).multiply(BigInteger.valueOf(other.denominator)).compareTo(
        //			BigInteger.valueOf(other.numerator).multiply(BigInteger.valueOf(denominator)));
    }

    public Rational add(Rational other) {
        return new Rational(numerator * other.denominator + denominator * other.numerator,
                denominator * other.denominator);
    }

    public Rational reverse() {
        if (numerator == 0) {
            throw new ArithmeticException();
        }
        return new Rational(denominator, numerator);
    }

    public Rational multiply(long number) {
        return new Rational(numerator * number, denominator);
    }

    public Rational subtract(Rational other) {
        return new Rational(numerator * other.denominator - denominator * other.numerator,
                denominator * other.denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rational rational = (Rational) o;

        if (denominator != rational.denominator) {
            return false;
        }
        if (numerator != rational.numerator) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (numerator ^ (numerator >>> 32));
        result = 31 * result + (int) (denominator ^ (denominator >>> 32));
        return result;
    }

    public Rational divide(long number) {
        return new Rational(numerator, denominator * number);
    }

    public long floor() {
        if (numerator >= 0) {
            return numerator / denominator;
        } else {
            return (numerator - denominator + 1) / denominator;
        }
    }

    public long ceil() {
        if (numerator >= 0) {
            return (numerator + denominator - 1) / denominator;
        } else {
            return numerator / denominator;
        }
    }

    public Rational divide(Rational other) {
        return new Rational(numerator * other.denominator, other.numerator * denominator);
    }

    public Rational multiply(Rational other) {
        return new Rational(numerator * other.numerator, other.denominator * denominator);
    }

    public double value() {
        return (double) numerator / denominator;
    }

    public Rational abs() {
        if (numerator >= 0) {
            return this;
        }
        return new Rational(-numerator, denominator);
    }
}
