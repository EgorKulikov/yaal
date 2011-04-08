package net.egork.numbers;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class MultiplicativeFunction {
	public static final MultiplicativeFunction DIVISOR_COUNT = new MultiplicativeFunction() {
		@Override
		protected long value(long p, int exponent, long power) {
			return exponent + 1;
		}
	};

	public static final MultiplicativeFunction DIVISOR_SUM = new MultiplicativeFunction() {
		@Override
		protected long value(long p, int exponent, long power) {
			return (power * p - 1) / (p - 1);
		}
	};

	public static final MultiplicativeFunction PHI = new MultiplicativeFunction() {
		@Override
		protected long value(long p, int exponent, long power) {
			return power / p * (p - 1);
		}
	};

	public static final MultiplicativeFunction MOBIUS = new MultiplicativeFunction() {
		@Override
		protected long value(long p, int exponent, long power) {
			return exponent == 1 ? -1 : 0;
		}
	};

	protected abstract long value(long p, int exponent, long power);

	public long calculate(long argument) {
		long value = 1;
		for (long i = 2; i * i <= argument; i++) {
			if (argument % i == 0) {
				int exponent = 0;
				long power = 0;
				do {
					exponent++;
					power *= i;
					argument /= i;
				} while (argument % i == 0);
				value *= value(i, exponent, power);
			}
		}
		if (argument != 1)
			value *= value(argument, 1, argument);
		return value;
	}
}
