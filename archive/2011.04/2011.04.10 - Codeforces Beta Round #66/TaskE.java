package April2011.CodeforcesBetaRound66;

import net.egork.collections.sequence.Array;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		int x = in.readInt();
		int[] a = in.readIntArray(n);
		if (x == 2) {
			out.println(0);
			return;
		}
		if (n == 0) {
			out.println(-1);
			return;
		}
		Arrays.sort(a);
		if (a[0] == 1) {
			out.println(1);
			return;
		}
		int[] primes = IntegerUtils.generatePrimes(Math.min(1300000, x));
		if (SequenceUtils.isSubSequence(Array.wrap(a), Array.wrap(primes)))
			out.println(primes.length);
		else
			out.println(-1);
	}
}

