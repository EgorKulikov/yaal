import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	private int[] primes = IntegerUtils.generatePrimes(1000000);

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long n = in.readLong();
		long result = 0;
		if (n > 1)
			result++;
		for (int p : primes) {
			if (n / p < p)
				break;
			long nCopy = n / p;
			while (nCopy >= p) {
				nCopy /= p;
				result++;
			}
		}
		out.println("Case #" + testNumber + ": " + result);
	}
}

