package April2011.EOlimpWeekly20;

import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n;
		try {
			n = in.readInt();
		} catch (InputMismatchException e) {
			Exit.exit(in, out);
			return;
		}
		int exponent = n;
		while (n % 10 == 0)
			n /= 10;
		double result = power(n, exponent);
		out.printf("%.0f\n", Math.floor(result));
	}

	private double power(int n, int exponent) {
		if (exponent == 0)
			return 1;
		double result = power(n, exponent / 2);
		result *= result;
		if (exponent % 2 == 1)
			result *= n;
		while (result > 10)
			result /= 10;
		return result;
	}
}

