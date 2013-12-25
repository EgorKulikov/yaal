package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TheFactorialConundrum {
	BigInteger answer;
	BigInteger original;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger modulo = in.readBigInteger();
		if (modulo.equals(BigInteger.ONE)) {
			out.printLine(1);
			return;
		}
		original = modulo;
		answer = BigInteger.ZERO;
		factorize(modulo, BigInteger.valueOf(2));
		out.printLine(answer.subtract(BigInteger.ONE));
	}

	private void factorize(BigInteger modulo, BigInteger start) {
		if (modulo.isProbablePrime(30)) {
			int exponent = 1;
			BigInteger copy = original.divide(modulo);
			while (copy.remainder(modulo).equals(BigInteger.ZERO)) {
				exponent++;
				copy = copy.divide(modulo);
			}
			update(modulo, exponent);
			return;
		}
		BigInteger x = start.mod(modulo);
		BigInteger x2 = x;
		BigInteger q = x;
		for (int i = 0; i < 1000; i++) {
			if (q.equals(BigInteger.ZERO)) {
				factorize(modulo, start.add(BigInteger.ONE));
				return;
			}
			BigInteger candidate = modulo.gcd(q);
			if (candidate.compareTo(BigInteger.ONE) > 0) {
				factorize(candidate, start);
				factorize(modulo.divide(candidate), start);
				return;
			}
			for (int j = 0; j < 100; j++) {
				x = x.multiply(x).subtract(BigInteger.ONE).mod(modulo);
				x2 = x2.multiply(x2).subtract(BigInteger.ONE).mod(modulo);
				x2 = x2.multiply(x2).subtract(BigInteger.ONE).mod(modulo);
				q = q.multiply(x.subtract(x2).abs()).mod(modulo);
			}
		}
		factorize(modulo, start.add(BigInteger.ONE));
	}

	final void update(BigInteger prime, int exponent) {
		answer = answer.max(prime);
		exponent--;
		BigInteger current = prime;
		while (exponent > 0) {
			current = current.add(BigInteger.ONE);
			answer = answer.max(current);
			BigInteger copy = current;
			do {
				copy = copy.divide(prime);
				exponent -= copy.intValue();
			} while (copy.compareTo(prime) >= 0);
		}
	}
}
