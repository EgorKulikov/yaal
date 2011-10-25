import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		out.printf("%.2f\n", n - 1 + n * (n - 1) / 4.);
	}
}

