package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskL implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		out.println(Integer.numberOfTrailingZeros(Integer.highestOneBit(n)) + 1);
	}
}

