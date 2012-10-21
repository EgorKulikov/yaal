import net.egork.collections.CollectionUtils;
import net.egork.collections.Pair;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskH implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int segmentCount = in.readInt();
		Pair<Integer, Integer>[] segments = IOUtils.readIntPairArray(in, segmentCount);
		boolean updated = true;
		while (updated) {
			updated = false;
			for (int j = 0; j < segmentCount; j++) {
				if (segments[j].first.equals(segments[j].second))
					continue;
				for (int k = j + 1; k < segmentCount; k++) {
					if (segments[k].first.equals(segments[k].second))
						continue;
					if (segments[j].first.equals(segments[k].first)) {
						if (segments[j].second <= segments[k].second)
							segments[k] = Pair.makePair(segments[j].second, segments[k].second);
						else
							segments[j] = Pair.makePair(segments[k].second, segments[j].second);
						updated = true;
					} else if (segments[j].second.equals(segments[k].second)) {
						if (segments[j].first >= segments[k].first)
							segments[k] = Pair.makePair(segments[k].first, segments[j].first);
						else
							segments[j] = Pair.makePair(segments[j].first, segments[k].first);
						updated = true;
					}
				}
			}
		}
		int result = CollectionUtils.count(Array.wrap(segments), new Filter<Pair<Integer, Integer>>() {
			public boolean accept(Pair<Integer, Integer> value) {
				return !value.first.equals(value.second);
			}
		});
		out.println(result);
	}
}

