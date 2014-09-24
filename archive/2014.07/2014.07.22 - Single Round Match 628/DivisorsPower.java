package net.egork;

import net.egork.numbers.MultiplicativeFunction;

import java.math.BigInteger;

public class DivisorsPower {
    public long findArgument(long n) {
		for (int i = (int) 1e9; ; i--) {
			if (BigInteger.valueOf(i).isProbablePrime(100)) {
				System.err.println((long)i * i);
				break;
			}
		}
		for (int i = 60; i >= 2; i--) {
			long candidate = Math.round(Math.pow(n, 1d / i));
			long copy = n;
			for (int j = 0; j < i; j++) {
				if (copy % candidate != 0) {
					copy = -1;
					break;
				}
				copy /= candidate;
			}
			if (copy == 1) {
				if (MultiplicativeFunction.DIVISOR_COUNT.calculate(candidate) == i) {
					return candidate;
				}
			}
		}
		return -1;
    }
}
