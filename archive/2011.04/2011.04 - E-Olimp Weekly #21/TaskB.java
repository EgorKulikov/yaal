package April2011.EOlympWeekly21;

import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		out.println(MiscUtils.josephProblem(n, k) + 1);
	}
}

