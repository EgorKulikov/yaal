package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[][] a = new int[n + 1][k + 2];
		for (int i = 1; i <= n; i++) {
			int l = in.readInt();
			int r = in.readInt();
			for (int j = l + 1; j <= r; j++) {
				a[i][j] = 1;
			}
			for (int j = 1; j <= k; j++) {
				a[i][j] += a[i - 1][j];
			}
		}
		int m = in.readInt();
		for (int i = 0; i < m; i++) {
			int l = in.readInt();
			int r = in.readInt();
			int cc = 0;
			int s = 0;
			for (int j = 0; j <= k + 1; j++) {
				int c = (a[r][j] > a[l - 1][j]) ? 1 : 0;
				if (c != cc) {
					s++;
				}
				cc = c;
			}
			out.printLine(s);
		}
	}
}
