package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Millenium {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long a = in.readInt();
		long b = in.readInt();
		int[] y = new int[n];
		for (int i = 0; i < n; ++i) {
			y[i] = in.readInt();
			in.readInt();
		}
		Arrays.sort(y);
		long res = 0;
		for (int i = 0; i < n; ++i) {
			if (i == 0 || y[i] > y[i - 1]) {
				res = Math.max(res, y[i] - 1 + (n - i + b - 1) / b);
			}
		}
		out.printLine(res);
	}
}
