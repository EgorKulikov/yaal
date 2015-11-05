package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {

	boolean[][] edge;
	int[][] edgeD;
	private boolean ok;
	private boolean[] z;
	int[] pp;
	private int n;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = in.readInt();
			}
		}
		int m = in.readInt();
		edge = new boolean[n][n];
		edgeD = new int[n][n];
		for (int i = 0; i < m; i++) {
			int x = in.readInt() - 1;
			int y = in.readInt() - 1;
			int d = in.readInt();
			if (edge[x][y]) {
				if (edgeD[x][y] != d) {
					out.printLine("Impossible");
					return;
				}
			} else {
				edge[x][y] = true;
				edgeD[x][y] = d;
				edge[y][x] = true;
				edgeD[y][x] = -d;
			}
		}
		ok = true;
		z = new boolean[n];
		pp = new int[n];
		for (int i = 0; i < n; i++) {
			if (!z[i]) dfs(i, 0);
		}
		if (!ok) {
			out.printLine("Impossible");
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				out.print((a[i][j] + pp[j] - pp[i]) + " ");
			}
			out.printLine();
		}
	}

	private void dfs(int i, int p) {
		if (z[i]) {
			if (pp[i] != p) ok = false;
			return;
		}
		z[i] = true;
		pp[i] = p;
		for (int j = 0; j < n; j++) {
			if (edge[i][j]) {
				dfs(j, p + edgeD[i][j]);
			}
		}
	}
}
