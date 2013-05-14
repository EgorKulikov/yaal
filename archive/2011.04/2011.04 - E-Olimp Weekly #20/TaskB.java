package April2011.EOlimpWeekly20;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		if (n <= 9) {
			out.println(Long.toString(IntegerUtils.factorial(n)).length());
			return;
		}
		out.printf("%.0f\n", Math.ceil(Math.log10(Math.sqrt(2 * Math.PI * n)) + n * Math.log10(n / Math.E)));
	}
}

