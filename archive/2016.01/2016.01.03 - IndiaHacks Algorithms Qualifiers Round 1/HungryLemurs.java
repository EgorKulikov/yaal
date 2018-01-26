package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class HungryLemurs {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= Math.max(n, k); i++) {
			int r = n % i;
			int candidate = Math.abs(k - i) + Math.min(r, i - r);
			answer = Math.min(answer, candidate);
		}
		out.printLine(answer);
	}
}
