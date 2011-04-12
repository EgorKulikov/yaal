package April2011.SnarknewsFirstAprilContest;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		long result = 2 * (n - 1) + 2 * (m - 1);
		result = Math.min(result, (long)n * m);
		result = Math.max(result,  1);
		out.println(result);
	}
}

