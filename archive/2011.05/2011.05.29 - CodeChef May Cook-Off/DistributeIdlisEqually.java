import net.egork.collections.ArrayUtils;
import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DistributeIdlisEqually implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		if (ArrayUtils.sumArray(array) % count != 0) {
			out.println(-1);
			return;
		}
		NavigableSet<Pair<Integer, Integer>> set = new TreeSet<Pair<Integer, Integer>>();
		for (int i = 0; i < count; i++)
			set.add(Pair.makePair(array[i], i));
		int answer = 0;
		while (!set.first().first.equals(set.last().first)) {
			answer++;
			Pair<Integer, Integer> first = set.pollFirst();
			Pair<Integer, Integer> last = set.pollLast();
			int delta = (last.first - first.first) / 2;
			set.add(Pair.makePair(first.first + delta, first.second));
			set.add(Pair.makePair(last.first - delta, last.second));
		}
		out.println(answer);
	}
}

