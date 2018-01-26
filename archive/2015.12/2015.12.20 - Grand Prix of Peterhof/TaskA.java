package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		out.printLine(calc(a, b, 10));
	}

	private int calc(int a, int b, int mod) {
		if (a == b) {
			return a % mod;
		} else {
			int q = 1 % mod;
			int k = 0;
			int[] r = new int[mod];
			Arrays.fill(r, -1);
			while (r[q] == -1) {
				r[q] = k;
				q = (q * a) % mod;
				k++;
			}
			int bb = calc(a + 1, b, k - r[q]);
			int res = 1;
			for (int i = 0; i < bb + k - r[q]; i++) {
				res = (res * a) % mod;
			}
			return res;
		}
	}
}
