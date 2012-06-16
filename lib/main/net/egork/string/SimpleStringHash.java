package net.egork.string;

import java.math.BigInteger;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SimpleStringHash extends AbstractStringHash {
	private static final long REVERSE_MULTIPLIER = BigInteger.valueOf(MULTIPLIER).modInverse(BigInteger.valueOf(2).pow(64)).longValue();

	private final long[] hash;
	private final long[] reversePower;

	public SimpleStringHash(CharSequence string) {
		hash = new long[string.length() + 1];
		long power = 1;
		reversePower = new long[hash.length];
		reversePower[0] = 1;
		for (int i = 0; i < hash.length - 1; i++) {
			hash[i + 1] = hash[i] + string.charAt(i) * power;
			power *= MULTIPLIER;
			reversePower[i + 1] = reversePower[i] * REVERSE_MULTIPLIER;
		}
	}

	public long hash(int from, int to) {
		return (hash[to] - hash[from]) * reversePower[from];
	}

	public int length() {
		return hash.length - 1;
	}
}
