package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[][] a = new int[n][n];
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				a[i][i + j + 1] = in.readInt();
			}
		}
		int[][] d = new int[n][k + 1];
		int[][] p = new int[n][k + 1];
		for (int i = 0; i < n - 1; i++) {
			d[i][1] = 0;
			for (int j = 0; j <= i; j++) {
				for (int t = i + 1; t < n; t++) {
					d[i][1] += a[j][t];
				}
			}
//			System.out.println(d[i][1]);
			int s = 0;
			for (int j = i - 1; j >= 0; j--) {
				for (int t = i + 1; t < n; t++) {
					s += a[j + 1][t];
				}
				for (int t = 1; t < k; t++) {
					int dd = d[j][t] + s;
					if (dd > d[i][t + 1]) {
						d[i][t + 1] = dd;
						p[i][t + 1] = j;
					}
				}
			}
		}
		int j = 0;
		for (int i = 0; i < n - 1; i++) {
			if (d[i][k] > d[j][k]) j = i;
		}
		out.printLine(d[j][k]);
		for (int i = k; i > 0; i--) {
			out.print((j + 1) + " ");
			j = p[j][i];
		}
	}
}
