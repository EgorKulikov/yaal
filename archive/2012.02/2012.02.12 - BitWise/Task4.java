package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task4 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int testCount = in.readInt();
		int mod = in.readInt();
		for (int i = 0; i < testCount; i++) {
			long typeCount = in.readInt();
			long length = in.readInt();
			long exponent = IntegerUtils.power(typeCount % (mod - 1), length, mod - 1);
			long result = IntegerUtils.power(3 % mod, exponent, mod);
			if (result == 0)
				result = mod;
			out.printLine(result);
		}
	}
}
