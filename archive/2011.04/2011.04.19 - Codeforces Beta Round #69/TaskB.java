package April2011.CodeforcesBetaRound69;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long a = in.readLong();
		long b = in.readLong();
		if (b == 0) {
			out.println(1);
			return;
		}
		if (a == 0) {
			out.println("0.5");
			return;
		}
		double denominator = 2 * a * b;
		double numerator = 2 * a * b;
		if (a >= 4 * b)
			numerator -= 2 * b * b;
		else {
			numerator -= a * b;
			numerator += a * a / 8.;
		}
		out.printf("%.9f\n", numerator / denominator);
	}
}

