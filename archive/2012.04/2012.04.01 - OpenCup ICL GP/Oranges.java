package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Oranges {
	BigInteger two = BigInteger.valueOf(2);
	BigInteger bigP;
	private int p;
	Map<BigInteger, Boolean> valid = new HashMap<BigInteger, Boolean>();
	private static final int MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		p = in.readInt();
		BigInteger n = in.readBigInteger();
		bigP = BigInteger.valueOf(p);
		int answer = go(n);
		if (n.mod(two).equals(BigInteger.ZERO) && isValid(n.divide(two)))
			answer += (MOD - 1) / 2;
		answer %= MOD;
		out.printLine(answer);
	}

	private int go(BigInteger n) {
		if (n.compareTo(BigInteger.ONE) <= 0)
			return 0;
		int remainder = n.mod(bigP).intValue();
		long answer = 0;
		if (remainder == 2) {
			if (two.equals(n))
				answer += (MOD + 1) / 2;
		}
		if (remainder == 1) {
			if (isValid(n.subtract(BigInteger.ONE)))
				answer++;
		}
		if (remainder == 0) {
			answer += go(n.divide(bigP));
			if (isValid(n.subtract(BigInteger.ONE)))
				answer++;
		}
		if (remainder == p - 1)
			answer += 2 * go(n.add(BigInteger.ONE).divide(bigP));
		if (remainder == p - 2)
			answer += go(n.add(two).divide(bigP));
		return (int) (answer % MOD);
	}

	private boolean isValid(BigInteger number) {
		if (valid.containsKey(number))
			return valid.get(number);
		boolean result;
		if (number.equals(BigInteger.ZERO))
			result = false;
		else if (number.equals(BigInteger.ONE))
			result = true;
		else {
			int remainder = number.mod(bigP).intValue();
			if (remainder != 0 && remainder != p - 1)
				result = false;
			else
				result = isValid(number.add(BigInteger.ONE).divide(bigP));
		}
		valid.put(number, result);
		return result;
	}
}
