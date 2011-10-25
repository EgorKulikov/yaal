import net.egork.collections.Pair;
import net.egork.collections.set.MultiSet;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		Pair<Integer, Integer>[] targets = IOUtils.readIntPairArray(in, count);
		MultiSet<Pair<Integer, Integer>> set = new MultiSet<Pair<Integer, Integer>>();
		for (int i = 0; i < count; i++) {
			int gcd = (int) IntegerUtils.gcd(Math.abs(targets[i].first), Math.abs(targets[i].second));
			targets[i] = Pair.makePair(targets[i].first / gcd, targets[i].second / gcd);
			set.add(targets[i]);
		}
		int result = 0;
		for (Pair<Integer, Integer> target : set)
			result = Math.max(result, set.get(target));
		out.println(result);
	}
}

