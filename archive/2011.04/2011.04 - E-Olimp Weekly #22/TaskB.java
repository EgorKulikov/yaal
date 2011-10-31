import net.egork.collections.CollectionUtils;
import net.egork.collections.Pair;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int[] x1 = new int[4];
		int[] y1 = new int[4];
		int[] x2 = new int[4];
		int[] y2 = new int[4];
		IOUtils.readIntArrays(in, x1, y1, x2, y2);
		int result = 0;
		for (int i = 1; i < 16; i++) {
			final int finalI = i;
			Filter<Pair<Integer, Integer>> filter = new Filter<Pair<Integer, Integer>>() {
				public boolean accept(Pair<Integer, Integer> value) {
					return (finalI >> value.second & 1) != 0;
				}
			};
			int maxX1 = CollectionUtils.maxElement(SequenceUtils.filter(Array.wrap(x1), filter));
			int minX2 = CollectionUtils.minElement(SequenceUtils.filter(Array.wrap(x2), filter));
			int maxY1 = CollectionUtils.maxElement(SequenceUtils.filter(Array.wrap(y1), filter));
			int minY2 = CollectionUtils.minElement(SequenceUtils.filter(Array.wrap(y2), filter));
			int square = Math.max(minX2 - maxX1, 0) * Math.max(minY2 - maxY1, 0);
			if (Integer.bitCount(i) % 2 == 1)
				result += square;
			else
				result -= square;
		}
		out.println(result);
	}
}

