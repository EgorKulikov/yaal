import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	final long MOD = 1000000007;

	long two(long n) {
		//System.err.println(n);
		if (n == 0) {
			return 1;
		}
		if (n % 2 == 0) {
			long t = two(n / 2);
			return ((t * t) % MOD);
		}
		long t = two(n - 1);
		return ((2 * t) % MOD);
	}

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		long n = in.readLong();
		long t = two(n);
		long a = t * t;
		a %= MOD;
		a *= (n - 3 + MOD) % MOD;
		a %= MOD;
		long b = t;
		b *= (n + 3) % MOD;
		b %= MOD;
		a += b + MOD;
		a %= MOD;
		out.println(a);
	}
}

