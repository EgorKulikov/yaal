package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndCinema {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		long m = in.readLong();
		long z = in.readLong();
		long l = in.readLong();
		long r = in.readLong();
		long b = in.readLong();
		long goodB = Math.min(Math.min(b, n + z), n * ((m + 1) / 2));
		long badB = Math.min(b, n * ((m + 1) / 2)) - goodB;
		long answer = Math.min(goodB + l + r + z, n * m);
		if (answer < n * m) {
			answer += Math.min((n * m - answer) / 2, badB);
		}
		out.printLine(answer);
	}
}
