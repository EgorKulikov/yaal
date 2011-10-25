package April2011.CodeforcesBetaRound67;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		long g = IntegerUtils.gcd(a, b);
		NavigableSet<Long> divisors = new TreeSet<Long>(IntegerUtils.getDivisors(g));
		int queryCount = in.readInt();
		for (int it = 0; it < queryCount; it++) {
			int low = in.readInt();
			int high = in.readInt();
			long result = divisors.headSet((long)high, true).last();
			if (result >= low)
				out.println(result);
			else
				out.println(-1);
		}
	}
}

