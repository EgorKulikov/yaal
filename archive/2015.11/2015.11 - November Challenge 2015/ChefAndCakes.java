package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndCakes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int withCake = n / IntegerUtils.gcd(n, m);
		if (withCake == n) {
			out.printLine("Yes");
		} else {
			out.printLine("No", withCake);
		}
	}
}
