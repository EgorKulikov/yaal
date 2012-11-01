package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskK {
	long[] answer = new long[1000000];
	Rational ONE = new Rational(1, 1);
	Rational ZERO = new Rational(0, 1);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long[] answer = solve(in.readInt());
		if (answer == null)
			out.printLine("Epic fail");
		else {
			out.printLine(answer.length);
			out.printLine(Array.wrap(answer).toArray());
		}
	}

	long[] solve(int count) {
		if (count < 100) {
			int answer = go(count, 0, 1, ONE);
			if (answer == -1)
				return null;
			return Arrays.copyOf(this.answer, answer);
		}
		if (count % 2 == 0) {
			long[] answer = solve((count - 2) / 2);
			long[] result = new long[answer.length + 1];
			for (int i = 0; i < answer.length; i++)
				result[i] = 2 * answer[i];
			result[answer.length] = 2;
			return result;
		}
		long[] answer = solve((count - 9) / 2);
		long[] result = new long[answer.length + 2];
		for (int i = 0; i < answer.length; i++)
			result[i] = 2 * answer[i];
		result[answer.length] = 3;
		result[answer.length + 1] = 6;
		return result;
	}

	private int go(long sum, int step, long min, Rational remaining) {
		if (sum == 0 && remaining.numerator.equals(BigInteger.ZERO)) {
			return step;
		}
		if (remaining.numerator.equals(BigInteger.ZERO))
			return -1;
		min = Math.max(min, (remaining.denominator.subtract(BigInteger.ONE)).divide(remaining.numerator).longValue() + 1);
		long max = (long) (Math.sqrt(remaining.denominator.multiply(BigInteger.valueOf(sum)).divide(remaining.numerator).longValue()) + 1e-7);
		for (long i = max; i >= min; i--) {
//			if (remaining.compareTo(new Rational(sum, i * i)) > 0)
//				return false;
			answer[step] = i;
			int answer = go(sum - i, step + 1, i, remaining.subtract(new Rational(1, i)));
			if (answer != -1)
				return answer;
		}
		return -1;
	}

	class Rational implements Comparable<Rational> {
		BigInteger numerator, denominator;

		public Rational(long numerator, long denominator) {
			this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
		}

		public Rational(BigInteger numerator, BigInteger denominator) {
			BigInteger gcd = numerator.gcd(denominator);
			this.numerator = numerator.divide(gcd);
			this.denominator = denominator.divide(gcd);
		}

		public int compareTo(Rational o) {
			return numerator.multiply(o.denominator).compareTo(o.numerator.multiply(denominator));
		}

		public Rational subtract(Rational rational) {
			return new Rational(numerator.multiply(rational.denominator).subtract(rational.numerator.multiply(denominator)), denominator.multiply(rational.denominator));
		}
	}
}
