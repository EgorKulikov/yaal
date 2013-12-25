package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskB {
	static final long MODULO = (long) (1e9 + 7);

	long[] p10;
	long[] zeroExtra;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String a = in.next();
		String b = in.next();
		String c = in.next();
		long res = 0;
		if (a.equals("0")) {
			if (c.equals("0")) ++res;
			a = "1";
		}
		p10 = new long[b.length() + 10];
		p10[0] = 1;
		for (int i = 1; i < p10.length; ++i)
			p10[i] = p10[i - 1] * 10 % MODULO;
		zeroExtra = new long[b.length() + 10];
		for (int i = 1; i < p10.length; ++i) {
			zeroExtra[i] = (zeroExtra[i - 1] + p10[i - 1]) % MODULO;
		}
		b = new BigInteger(b).add(BigInteger.ONE).toString();
		out.printLine((res + count(b, c) - count(a, c) + MODULO) % MODULO);
    }

	private long count(String upto, String substr) {
		long res = 0;
		boolean[] match = new boolean[substr.length() + 1];
		match[0] = true;
		int numTotal = 0;
		for (int firstLess = 0; firstLess < upto.length(); ++firstLess) {
			int maxDigit = upto.charAt(firstLess) - '0';
			for (int digit = 0; digit < maxDigit; ++digit) {
				res = (res + numTotal * p10[upto.length() - firstLess - 1]) % MODULO;
				if (firstLess == 0 && digit == 0 && substr.equals("0")) {
					res = (res - zeroExtra[upto.length() - 1] + MODULO) % MODULO;
				}
				if (firstLess + 1 + substr.length() <= upto.length())
					res = (res + p10[upto.length() - firstLess - 1 - substr.length()] * (1 + upto.length() - firstLess - 1 - substr.length())) % MODULO;
				for (int overlap = 0; overlap < substr.length(); ++overlap) {
					if (!match[overlap]) continue;
					if (overlap == 0 && firstLess == 0 && digit == 0) continue;
					if (digit == substr.charAt(overlap) - '0' && substr.length() - overlap - 1 <= upto.length() - firstLess - 1) {
						res = (res + p10[upto.length() - firstLess - 1 - (substr.length() - overlap - 1)]) % MODULO;
					}
				}
			}
			for (int pos = substr.length() - 1; pos >= 0; --pos) {
				match[pos + 1] = match[pos] && upto.charAt(firstLess) == substr.charAt(pos);
			}
			match[0] = true;
			if (match[substr.length()]) ++numTotal;
		}
		return res;
	}
}
