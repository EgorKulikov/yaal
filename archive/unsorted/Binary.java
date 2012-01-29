package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Binary {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int q = in.readInt();
		long[] w = new long[n];
		for (int i = 0; i < n; i++) {
			w[i] = in.readLong();
		}
		int[] d = new int[n];
		int[][] e = new int[2][n];
		for (int i = 0; i < q; i++) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			if (d[a] == 2) throw new RuntimeException();
			e[d[a]][a] = b;
			d[a]++;
		}

		int[] p = new int[n];
		for (int i = 0; i < n; i++) p[i] = i;
		sort(p, w, 0, n - 1);

		int[] pr = new int[m];
		int[] sn = new int[m];
		for (int i = 0; i < m; i++) {
			pr[i] = i;
			sn[i] = i;
		}

		long res = 0;

		for (int ii = 0; ii < n; ii++) {
			int i = p[ii];
			for (int j = 0; j < d[i]; j++) {
				int c = e[j][i];
				int cc = sn[get(pr, c)];
				if (cc >= 0) {
					res += w[i];
					if (d[i] == 2) {
						int z = e[1 - j][i];
						int zz = sn[get(pr, z)];
						if (get(pr, c) == get(pr, z)) {
							sn[get(pr, z)] = -1;
						} else {
							unite(pr, c, z);
							sn[get(pr, z)] = zz;
						}
					} else {
						sn[get(pr, c)] = -1;
					}
				    break;
				}
			}
		}

		out.printLine(res);

	}

	private void unite(int[] pr, int a, int b) {
		a = get(pr, a);
		b = get(pr, b);
		if (a != b) {
			pr[a] = b;
		}
	}

	private int get(int[] pr, int c) {
		if (pr[c] == c) return c;
		pr[c] = get(pr, pr[c]);
		return pr[c];
	}

	private void sort(int[] p, long[] w, int l, int r) {
		int i = l;
		int j = r;
		long x = w[p[(l + r) / 2]];
		while (i <= j) {
			while (w[p[i]] > x) i++;
			while (w[p[j]] < x) j--;
			if (i <= j) {
				int t = p[i];
				p[i] = p[j];
				p[j] = t;
				i++;
				j--;
			}
		}
		if (l < j) sort(p, w, l, j);
		if (i < r) sort(p, w, i, r);
	}
}
