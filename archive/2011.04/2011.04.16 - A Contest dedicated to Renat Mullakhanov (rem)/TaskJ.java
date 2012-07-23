package April2011.UVaAContestDedicatedToRenatMullakhanov;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskJ implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long bottleCount = in.readLong();
		int result = Long.numberOfTrailingZeros(Long.highestOneBit(bottleCount)) + 1;
		if (bottleCount == 0)
			result = 0;
		out.println("Case " + testNumber + ": " + result);
	}
}

