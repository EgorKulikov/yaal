import net.egork.collections.CollectionUtils;
import net.egork.collections.Pair;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.Array;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.List;

public class TaskG implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String[] pages = new String[10];
		final int[] rank = new int[10];
		for (int i = 0; i < 10; i++) {
			pages[i] = in.readString();
			rank[i] = in.readInt();
		}
		final int maxRank = CollectionUtils.maxElement(Array.wrap(rank));
		List<String> answer = SequenceUtils.filter(Array.wrap(pages), new Filter<Pair<String, Integer>>() {
			public boolean accept(Pair<String, Integer> value) {
				return rank[value.second] == maxRank;
			}
		});
		out.println("Case #" + testNumber + ":");
		for (String page : answer)
			out.println(page);
	}
}

