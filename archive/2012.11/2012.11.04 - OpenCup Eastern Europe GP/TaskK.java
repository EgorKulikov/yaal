package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
		}
		if (2 * k >= n) {
			int res = 0;
			for (int x : a) res += x;
			out.printLine(res);
			return;
		}
		int res = -1;
		for (int borderMode = 0; borderMode < 3; ++borderMode) {
			int[][][] best = new int[n + 1][2][k + 1];
			for (int[][] z : best) for (int[] b : z) Arrays.fill(b, -1);
			switch (borderMode) {
				case 0:
					best[0][0][0] = 0;
					break;

				case 1:
					best[1][1][1] = a[0];
					break;

				case 2:
					best[0][1][0] = 0;
					break;
			}
			for (int at = 0; at <= n; ++at) {
				for (int canTwo = 1; canTwo >= 0; --canTwo) {
					for (int spent = 0; spent <= k; ++spent) {
						int cbest = best[at][canTwo][spent];
						if (cbest < 0) continue;
						if (at < n)
							best[at + 1][0][spent] = Math.max(best[at + 1][0][spent], cbest);
						if (spent < k)
							best[at][canTwo][spent + 1] = Math.max(best[at][canTwo][spent + 1], cbest);
						if (canTwo == 1)
							best[at][0][spent] = Math.max(best[at][0][spent], cbest);
						if (canTwo == 0 && spent < k && at < n) {
							best[at + 1][1][spent + 1] = Math.max(best[at + 1][1][spent + 1], cbest + a[at]);
						}
						if (canTwo == 1 && spent < k && at + 1 < n) {
							best[at + 2][1][spent + 1] = Math.max(best[at + 2][1][spent + 1], cbest + a[at] + a[at + 1]);
						}
					}
				}
			}
			switch (borderMode) {
				case 0:
					res = Math.max(res, best[n][0][k]);
					break;

				case 1: {
					int csum = 0;
					for (int tail = 0; tail < k; ++tail) {
						res = Math.max(res, best[n - 2 * tail][0][k - tail] + csum);
						if (tail + 1 < k) {
							csum += a[n - 1 - tail * 2];
							csum += a[n - 2 - tail * 2];
						}
					}
					break;
				}

				case 2:
					res = Math.max(res, best[n][1][k]);
					break;
			}
		}
		out.printLine(res);
	}
}
