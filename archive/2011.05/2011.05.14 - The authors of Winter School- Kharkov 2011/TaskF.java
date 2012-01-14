import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskF implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		out.println(go(to) - go(from - 1));
	}

	private long go(long n) {
		return n * (n + 1) / 2;
	}
}

