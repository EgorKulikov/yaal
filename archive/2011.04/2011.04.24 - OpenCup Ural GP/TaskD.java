import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		double k = in.readInt();
		int m = in.readInt();
		boolean[][] a = new boolean[n][n];
		for (int i = 0; i < m; i++) {
			int x = in.readInt();
			int y = in.readInt();
			a[x - 1][y - 1] = true;
			a[y - 1][x - 1] = true;
		}
		boolean[] z = new boolean[n];
		boolean[] zl = new boolean[n];
		boolean[] zr = new boolean[n];
		int[] p = new int[n];
		Arrays.fill(p, -1);
		for (int i = 0; i < n; i++) {
			Arrays.fill(z, false);
			if (dfs(i, a, z, p)) {
				zl[i] = true;
			}
		}
		Arrays.fill(z, false);
		for (int i = 0; i < n; i++) {
			if (!zl[i]) dfs2(i, a, z, p, zr);
		}
		double[] res = new double[n];
		for (int i = 0; i < n; i++) {
			if (!z[i]) res[i] += k / 2;
			if (zr[i]) res[i] += k / 2;
		}
		for (double v : res) {
			out.println(v);
		}
	}

	private void dfs2(int i, boolean[][] a, boolean[] z, int[] p, boolean[] zr) {
		if (z[i]) return;
		z[i] = true;
		for (int j = 0; j < a.length; j++) {
			if (a[i][j] && p[j] != i) {
				zr[j] = true;
				if (p[j] != -1) dfs2(p[j], a, z, p, zr);
			}
		}
	}

	private boolean dfs(int i, boolean[][] a, boolean[] z, int[] p) {
		if (z[i]) return false;
		z[i] = true;
		for (int j = 0; j < a.length; j++) {
			if (a[i][j]) {
				if (p[j] == -1) {
					p[j] = i;
					return true;
				} else {
					if (dfs(p[j], a, z, p)) {
						p[j] = i;
						return true;
					}
				}
			}
		}
		return false;
	}
}

