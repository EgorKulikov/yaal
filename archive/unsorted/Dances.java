package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Dances {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int mod = (int)(1e9 + 7);
		int n = in.readInt();
		int r = in.readInt();
		long answer = 1;
		for (int i = n - r + 1; i <= n; i++) {
			answer = answer * i % mod * i % mod;
		}
		long fac = 1;
		for (int i = 2; i <= r; i++)
			fac = fac * i % mod;
		answer = answer * BigInteger.valueOf(fac).modInverse(BigInteger.valueOf(mod)).longValue() % mod;
		out.printLine(answer);
	}
}
