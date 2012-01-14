package April2011.UVaAContestDedicatedToRenatMullakhanov;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	private int[] primes;
	private static final long MOD = 10000019;

	public TaskA() {
		primes = IntegerUtils.generatePrimes(100001);
	}

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		long result = 1;
		boolean found = false;
		int n = in.readInt();
		int t = in.readInt();
		for (int p : primes) {
			long count = IntegerUtils.powerInFactorial(n, p);
			if (count < t)
				break;
			found = true;
			result = (result * IntegerUtils.power(p, count / t, MOD)) % MOD;
		}
		if (!found)
			result = -1;
		out.println("Case " + testNumber + ": " + result);
	}
}

