package April2011.UVaAContestDedicatedToRenatMullakhanov;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskH implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		double c = in.readInt();
		double f = in.readInt();
		c += 5 * f / 9;
		out.printf("Case %d: %.2f\n", testNumber, c);
	}
}

