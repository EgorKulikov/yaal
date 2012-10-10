import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskF implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		long result = 0;
		long numerator = IntegerUtils.factorial(count);
		long denominator = 1;
		for (int i = 0; i <= count; i++) {
			if (i % 2 == 0)
				result += numerator / denominator;
			else
				result -= numerator / denominator;
			denominator *= i + 1;
		}
		out.println(result + "/" + numerator);
	}
}

