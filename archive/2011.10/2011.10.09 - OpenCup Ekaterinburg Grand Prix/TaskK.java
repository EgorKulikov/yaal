import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskK implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] sq = new int[n];
		for (int i = 0; i < n; i++) {
			sq[i] = in.readInt();
		}
		int[] l = new int[n];
		int[] r = new int[n];
		for (int i = 0; i < n; i++) {
			l[i] = in.readInt();
			r[i] = in.readInt();
		}
		boolean[][] a = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = l[j] <= sq[i] && r[j] >= sq[i];
			}
		}
		int[] p = new int[n];
		for (int i = 0; i < n; i++) p[i] = i;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (sq[p[j]] < sq[p[i]]) {
					int t = p[i];
					p[i] = p[j];
					p[j] = t;
				}
			}
		}
		int[] pr = new int[n];
		Arrays.fill(pr, -1);
		for (int i : p) {
			int min = -1;
			for (int j = 0; j < n; j++) {
				if (l[j] <= sq[i] && r[j] >= sq[i] && pr[j] == -1) {
					if (min == -1 || r[j] < r[min]) min = j;
				}
			}
//			System.out.println(i + " " + min);
			if (min == -1) {
				out.println("Let's search for another office.");
				return;
			}
			pr[min] = i;
		}
		int[] z = new int[n];
		for (int i = 0; i < n; i++) {
			if (dfs(i, a, z, pr)) {
				out.println("Ask Shiftman for help.");
				return;
			}
		}
		out.println("Perfect!");
		for (int i = 0; i < n; i++) {
			out.print((pr[i] + 1) + " ");
		}
		out.println();
	}

	private boolean dfs(int i, boolean[][] a, int[] z, int[] pr) {
		if (z[i] == 1) return true;
		if (z[i] == 2) return false;
		z[i] = 1;
		for (int j = 0; j < a.length; j++) if (a[i][j] && pr[j] != i) {
			if (dfs(pr[j], a, z, pr)){
				return true;
			}
		}
		z[i] = 2;
		return false;
	}
}

