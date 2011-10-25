import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskH implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = in.readInt();
			}
		}
		int res = 0;
		for (int k = 0; k < n - 1; k++) {
			int s = 0;
			for (int i = 0; i <= k; i++) {
				for (int j = k + 1; j < n; j++) {
					s += a[i][j];
				}
			}
			res = Math.max(res, s);
			s = 0;
			for (int i = 0; i <= k; i++) {
				for (int j = k + 1; j < n; j++) {
					s += a[j][i];
				}
			}
			res = Math.max(res,  s);
		}
		out.println((res + 35) / 36);

	}
}

