package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int p = in.readInt();
		int q = in.readInt();
		String s = in.readString();
		for (int i = 0; i <= n; i += p) {
			if ((n - i) % q == 0) {
				out.printLine(i / p + (n - i) / q);
				for (int j = 0; j < i; j += p) {
					out.printLine(s.substring(j, j + p));
				}
				for (int j = i; j < n; j += q) {
					out.printLine(s.substring(j, j + q));
				}
				return;
			}
		}
		out.printLine(-1);
	}
}
