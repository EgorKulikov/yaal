package April2011.EOlimpWeekly20;

import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n;
		try {
			n = in.readInt();
		} catch (InputMismatchException e) {
			Exit.exit(in, out);
			return;
		}
		long result = 0;
		List<Pair<Long, Integer>> divisors = IntegerUtils.factorize(n);
		for (int i = 0; i < 1 << divisors.size(); i++) {
			long current = n;
			for (int j = 0, divisorsSize = divisors.size(); j < divisorsSize; j++) {
				Pair<Long, Integer> divisor = divisors.get(j);
				if ((i >> j & 1) != 0) {
					current = current / divisor.first() * (divisor.first() - 1);
					current *= divisor.second();
				}
			}
			result += current;
		}
		out.println(result);
	}
}

