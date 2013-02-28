package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	private Graph g1;
	private Graph g2;
	private byte[][] z;
	private int[][] d;
	private int[][] pi;
	private int[][] pj;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();

		g1 = new Graph(in);
		g2 = new Graph(in);
		z = new byte[g1.n][g2.n];
		d = new int[g1.n][g2.n];
		pi = new int[g1.n][g2.n];
		pj = new int[g1.n][g2.n];
		cycle = false;
		max = 0;
		maxi = -1;
		maxj = -1;

		for (int i = 0; i < g1.n; i++) {
			for (int j = 0; j < g2.n; j++) if (g1.l[i] == g2.l[j]) {
				dfs(i, j);
			}
		}

		if (cycle) {
			out.printLine("Case " + testNumber + ": -1");
		} else {
			out.printLine("Case " + testNumber + ": " + max);
			int i = maxi;
			int j = maxj;
			int[] p1 = new int[max];
			int[] p2 = new int[max];
			int c = 0;
			while (i >= 0) {
				int ii = pi[i][j];
				int jj = pj[i][j];
				p1[c] = i + 1;
				p2[c] = j + 1;
				c++;
				i = ii;
				j = jj;
			}
			out.printLine(p1);
			out.printLine(p2);
		}

    }
	boolean cycle;
	int max;
	int maxi;
	int maxj;

	private void dfs(int i, int j) {
		if (z[i][j] == 1) {
			cycle = true;
			return;
		}
		if (z[i][j] == 2) return;
		z[i][j] = 1;

		d[i][j] = 1;
		pi[i][j] = -1;
		pj[i][j] = -1;
		for (int ii = g1.h[i]; ii >= 0; ii = g1.nx[ii]) {
			for (int jj = g2.h[j]; jj >= 0; jj = g2.nx[jj]) {
				int iii = g1.ds[ii];
				int jjj = g2.ds[jj];
				if (g1.l[iii] == g2.l[jjj]) {
					dfs(iii, jjj);
					if (d[iii][jjj] + 1 > d[i][j]) {
						d[i][j] = d[iii][jjj] + 1;
						pi[i][j] = iii;
						pj[i][j] = jjj;
					}
				}
			}
		}
		if (d[i][j] > max) {
			max = d[i][j];
			maxi = i;
			maxj = j;
		}

		z[i][j] = 2;
	}

	class Graph {
		int n;
		int m;
		int[] h;
		int[] nx;
		int[] ds;
		char[] l;

		public Graph(InputReader in) {
			n = in.readInt();
			m = in.readInt();
			h = new int[n];
			Arrays.fill(h, -1);
			nx = new int[m];
			ds = new int[m];
			l = in.readString().toCharArray();
			for (int i = 0; i < m; i++) {
				int x = in.readInt() - 1;
				int y = in.readInt() - 1;
				ds[i] = y;
				nx[i] = h[x];
				h[x] = i;
			}
		}

	}
}
