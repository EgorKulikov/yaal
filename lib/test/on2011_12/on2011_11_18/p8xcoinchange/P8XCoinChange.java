package on2011_12.on2011_11_18.p8xcoinchange;



import net.egork.numbers.IntegerUtils;

public class P8XCoinChange {
	public int solve(long coins_sum, long[] values) {
		Polynom polynom = new Polynom(new long[]{1});
		for (int i = 1; i < values.length; i++) {
			long delta = values[i] / values[i - 1];
			polynom = polynom.sum(delta, coins_sum % delta);
			coins_sum /= delta;
		}
		return (int) polynom.value(coins_sum);
	}


}

class Polynom {
	private static final long[] reverse = new long[101];

	private final long[] coefficient;
	private static final long MOD = 1000003;
	private static final long[][] c = IntegerUtils.generateBinomialCoefficients(101, MOD);

	static {
		for (int i = 1; i <= 100; i++)
			reverse[i] = IntegerUtils.reverse(i, MOD);
	}

	Polynom(long[] coefficient) {
		this.coefficient = coefficient;
	}
	
	long value(long x) {
		x %= MOD;
		if (x < coefficient.length)
			return coefficient[((int) x)];
		long power = 1;
		long result = 0;
		for (int i = 0; i < coefficient.length; i++) {
			long current = 0;
			for (int j = 0; j <= i; j++) {
				if (((i - j) & 1) == 0)
					current += coefficient[j] * c[i][j] % MOD;
				else
					current -= coefficient[j] * c[i][j] % MOD;
			}
			result += current * power % MOD;
			power *= x - i;
			power %= MOD;
			power *= reverse[i + 1];
			power %= MOD;
		}
		result %= MOD;
		result += MOD;
		result %= MOD;
		return result;
	}

	Polynom sum(long step, long first) {
		long[] argument = new long[coefficient.length + 1];
		long[] value = new long[coefficient.length + 1];
		first %= MOD;
		for (int i = 0; i <= coefficient.length; i++) {
			argument[i] = i;
			value[i] = value(first);
			if (i > 0)
				value[i] = (value[i] + value[i - 1]) % MOD;
			first = (first + step) % MOD;
		}
		return new Polynom(value);
	}
}