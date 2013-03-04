package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		if (s.equals("X")) throw new UnknownError();
		int n = s.length();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = s.charAt(i) - 'A';
		}
		out.printLine(solve(a, n, 1));
	}

	private long solve(int[] a, int n, int d) {
		if (n == 0) return 0;
		if (a[n - 1] == d) return solve(a, n - 1, d);
		int dd = 3 - d - a[n - 1];
		return solve(a, n - 1, dd) + 1 + ((1L << (n - 1)) - 1);
	}
}
