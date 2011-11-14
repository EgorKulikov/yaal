package April2011.UVaAContestDedicatedToRenatMullakhanov;

import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskF implements Solver {
	private int[][] calculated = new int[1001][1001];
	private long[][] result = new long[1001][1001];
	private static final long MOD = 1000000007;

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		long result = go(0, 0, testNumber, in.readString().toCharArray());
		out.println("Case " + testNumber + ": " + result);
	}

	private long go(int step, int u, int testNumber, char[] s) {
		if (u < 0)
			return 0;
		if (calculated[step][u] == testNumber)
			return result[step][u];
		calculated[step][u] = testNumber;
		if (step == s.length)
			return result[step][u] = u == 0 ? 1 : 0;
		if (s[step] == 'E')
			return result[step][u] = go(step + 1, u, testNumber, s);
		if (s[step] == 'U')
			return result[step][u] = ((u + 1) * go(step + 1, u + 1, testNumber, s) + u * go(step + 1, u, testNumber, s)) % MOD;
		return result[step][u] = (u * (go(step + 1, u - 1, testNumber, s) + go(step + 1, u, testNumber, s))) % MOD;
	}
}

