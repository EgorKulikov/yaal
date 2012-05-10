package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {

	private static final int SIZE = 200000;
	private static final int INF = 1000000000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[][] d = new int[SIZE][26];
		boolean[] f = new boolean[SIZE];
		int m = 1;
		for (int i = 0; i < n; i++) {
			String s = in.readString();
			int cur = 0;
			for (int j = 0; j < s.length(); j++) {
				int c = s.charAt(j) - 'a';
				if (d[cur][c] == 0) {
					d[cur][c] = m;
					m++;
				}
				cur = d[cur][c];
			}
			f[cur] = true;
		}

		int[] r = new int[SIZE];
		int[] a = new int[SIZE];
		int[] b = new int[SIZE];
		for (int i = m - 1; i >= 0; i--) {
			if (f[i]) {
				r[i] = 1;
			} else {
				for (int c = 0; c < 26; c++) {
					if (d[i][c] > 0) {
						r[i] += r[d[i][c]];
					}
				}
			}
		}
		for (int i = m - 1; i >= 0; i--) {
			if (f[i]) {
				a[i] = 0;
				b[i] = 0;
			} else {
				int s = 0;
				int k = 0;
				for (int c = 0; c < 26; c++) {
					if (d[i][c] > 0) {
						k += r[d[i][c]];
						s += a[d[i][c]];
					}
				}
				a[i] = INF;
				b[i] = INF;
				for (int c = 0; c < 26; c++) if (d[i][c] > 0) {
					a[i] = Math.min(
						a[i],
						s - a[d[i][c]] + b[d[i][c]] + k
					);
					b[i] = Math.min(
						b[i],
						s - a[d[i][c]] + b[d[i][c]] +
							k - r[d[i][c]]
					);
				}
			}
		}
		out.printLine(a[0]);
	}
}
