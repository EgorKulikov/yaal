package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class Funny {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		if (n == 0) throw new UnknownError();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.readInt();
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] p = new int[n];
		for (int i = 0; i < n; i++) {
			Integer last = map.get(a[i]);
			if (last == null) {
				p[i] = -1;
			} else {
				p[i] = last;
			}
			map.put(a[i], i);
		}
		int[][] d = new int[n][k + 1];
		int[][] mx = new int[n][k + 1];
		int[][] pp = new int[n][k + 1];
		int ii = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k && j <= i + 1; j++) {
				if (p[i] >= 0) {
					d[i][j] = d[p[i]][j] + 1;
					pp[i][j] = pp[p[i]][j];
				}
				int zz = i == 0 ? 1 : (d[mx[i - 1][j - 1]][j - 1] + 1);
				if (zz >= d[i][j]) {
					d[i][j] = zz;
					pp[i][j] = i == 0 ? -1 : mx[i - 1][j - 1];
				}
				mx[i][j] = i;
				if (i > 0 && d[mx[i - 1][j]][j] > d[i][j]) {
					mx[i][j] = mx[i - 1][j];
				}
			}
			if (d[i][k] > d[ii][k]) ii = i;
		}
		int[] b = new int[k];
		out.printLine(d[ii][k]);
		for (int i = 0; i < k; i++) {
			b[k - i - 1] = pp[ii][k - i];
			ii = pp[ii][k - i];
		}
		for (int i = 1; i < k; i++) {
			out.printLine(b[i] + 1);
		}
	}
}
