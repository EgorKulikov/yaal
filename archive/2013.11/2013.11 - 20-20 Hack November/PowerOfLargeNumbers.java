package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PowerOfLargeNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long base = readNumber(in, (long) (1e9 + 7));
		long exponent = readNumber(in, (long) (1e9 + 6));
		if (base == 0)
			out.printLine(0);
		else
			out.printLine(IntegerUtils.power(base, exponent, (long) (1e9 + 7)));
    }

	private long readNumber(InputReader in, long mod) {
		long result = 0;
		int c;
		while (Character.isDigit(c = in.read())) {
			result *= 10;
			result += c - '0';
			result %= mod;
		}
		return result;
	}
}
