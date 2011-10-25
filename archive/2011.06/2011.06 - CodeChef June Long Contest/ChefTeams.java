import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.NavigableSet;
import java.util.TreeSet;

public class ChefTeams implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		NavigableSet<Pair<Integer, Integer>> young = new TreeSet<Pair<Integer, Integer>>();
		NavigableSet<Pair<Integer, Integer>> senior = new TreeSet<Pair<Integer, Integer>>();
		int difference = 0;
		for (int i = 0; i < count; i++) {
			Pair<Integer, Integer> chef = IOUtils.readIntPair(in);
			if (senior.isEmpty() || chef.first < senior.first().first) {
				young.add(chef);
				difference += chef.second;
			} else {
				senior.add(chef);
				difference -= chef.second;
			}
			if (young.size() < senior.size()) {
				chef = senior.pollFirst();
				young.add(chef);
				difference += 2 * chef.second;
			}
			if (young.size() > senior.size() + 1) {
				chef = young.pollLast();
				senior.add(chef);
				difference -= 2 * chef.second;
			}
			out.println(Math.abs(difference));
		}
	}
}

