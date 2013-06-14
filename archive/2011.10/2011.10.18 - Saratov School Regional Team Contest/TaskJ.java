import net.egork.collections.Pair;
import net.egork.collections.sequence.Array;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskJ implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] x = new int[3 * count];
		int[] y = new int[3 * count];
		for (int i = 0; i < count; i++) {
			x[i] = in.readInt();
			y[i] = in.readInt();
			x[i + count] = -x[i];
			y[i + count] = y[i];
			x[i + 2 * count] = x[i];
			y[i + 2 * count] = -y[i];
		}
		Integer[] order = SequenceUtils.order(Array.wrap(x));
		Pair<Integer, Integer> vectors = minDistance(order, x, y, 0, 3 * count).second;
		out.println((vectors.first % count + 1) + " " + (vectors.first / count + 1) + " " + (vectors.second % count + 1)
			+ " " + (4 - vectors.second / count));
	}

	private Pair<Integer, Pair<Integer, Integer>> minDistance(Integer[] order, int[] x, final int[] y, int from, int to)
	{
		if (to - from == 1)
			return Pair.makePair(Integer.MAX_VALUE / 4, Pair.makePair(-1, -1));
		int middle = (from + to) / 2;
		Pair<Integer, Pair<Integer, Integer>> result = MiscUtils.min(minDistance(order, x, y, from, middle),
			minDistance(order, x, y, middle, to));
		int splitX = x[order[middle]];
		List<Integer> viable = new ArrayList<Integer>();
		for (int i = middle; i < to; i++) {
			if (x[order[i]] < splitX + result.first)
				viable.add(order[i]);
			else
				break;
		}
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return y[o1] - y[o2];
			}
		};
		Collections.sort(viable, comparator);
		Arrays.sort(order, from, middle, comparator);
		int start = 0;
		for (int i = from; i < middle; i++) {
			int index = order[i];
			while (start < viable.size()) {
				if (y[viable.get(start)] <= y[index] - result.first)
					start++;
				else
					break;
			}
			for (int j = start; j < viable.size(); j++) {
				int index2 = viable.get(j);
				if (index % (order.length / 3) == index2 % (order.length / 3))
					continue;
				if (y[index2] - y[index] >= result.first)
					break;
				int distance = sqr(x[index] - x[index2]) + sqr(y[index] - y[index2]);
				if (distance < result.first)
					result = Pair.makePair(distance, Pair.makePair(index, index2));
			}
		}
		return result;
	}

	private int sqr(int value) {
		return value * value;
	}
}

