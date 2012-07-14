import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AngryChefCrispyChips implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int size = in.readInt();
		int threshold = in.readInt();
		int[] villages = IOUtils.readIntArray(in, size);
		int queryCount = in.readInt();
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		IOUtils.readIntArrays(in, from, to);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int colors = 0;
		for (int i = 0; i < size; i++) {
			Integer color = map.get(villages[i]);
			if (color == null) {
				color = colors++;
				map.put(villages[i], color);
			}
			villages[i] = color;
		}
		int[] next = new int[size];
		int[] ind = new int[colors];
		Arrays.fill(ind, -1);
		for (int i = size - 1; i >= 0; i--) {
			next[i] = ind[villages[i]];
			ind[villages[i]] = i;
		}
		TreapSet<Integer> tree = new TreapSet<Integer>();
		int[] current = new int[colors];
		Arrays.fill(current, -2);
		for (int i = 0; i < size; i++) {
			if (current[villages[i]] != -2)
				continue;
			int index = i;
			for (int j = 0; j < threshold && index != -1; j++)
				index = next[index];
			current[villages[i]] = index;
			if (index != -1)
				tree.add(index);
		}
		int[] count = new int[size];
		for (int i : from)
			count[i]++;
		int[][] toProcess = new int[size][];
		for (int i = 0; i < size; i++)
			toProcess[i] = new int[count[i]];
		for (int i = 0; i < queryCount; i++)
			toProcess[from[i]][--count[from[i]]] = i;
//		Integer[] order = ListUtils.order(Array.wrap(from));
		int[] answer = new int[queryCount];
		for (int i = 0; i < size; i++) {
			for (int j : toProcess[i])
				answer[j] = tree.headSet(to[j], true).size();
			int index = current[villages[i]];
			if (index != -1) {
				tree.remove(index);
				index = next[index];
				if (index != -1)
					tree.add(index);
				current[villages[i]] = index;
			}
		}
		for (int value : answer)
			out.println(value);
	}
}

class IntervalTree {
	private int[] left;
	private int[] right;
	private int[] value;

	public IntervalTree(int size) {
		left = new int[4 * size];
		right = new int[4 * size];
		value = new int[4 * size];
		init(0, 0, size);
	}

	private void init(int root, int left, int right) {
		this.left[root] = left;
		this.right[root] = right;
		if (right - left > 1) {
			init(2 * root + 1, left, (left + right) / 2);
			init(2 * root + 2, (left + right) / 2, right);
		}
	}

	public void put(int index, int value) {
		put(index, value, 0);
	}

	private void put(int index, int value, int root) {
		if (index >= right[root] || index < left[root])
			return;
		this.value[root] += value;
		if (right[root] - left[root] > 1) {
			put(index, value, 2 * root + 1);
			put(index, value, 2 * root + 2);
		}
	}

	public int getSegment(int left, int right) {
		return getSegment(left, right, 0);
	}

	private int getSegment(int left, int right, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return 0;
		if (left <= this.left[root] && right >= this.right[root])
			return value[root];
		return getSegment(left, right, 2 * root + 1) + getSegment(left, right, 2 * root + 2);
	}
}
