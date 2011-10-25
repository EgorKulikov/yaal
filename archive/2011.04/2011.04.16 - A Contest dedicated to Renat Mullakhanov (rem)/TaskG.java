package April2011.UVaAContestDedicatedToRenatMullakhanov;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.*;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class TaskG implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rectangleCount = in.readInt();
		int coverSize = in.readInt();
		int[] x1 = new int[rectangleCount];
		int[] y1 = new int[rectangleCount];
		int[] x2 = new int[rectangleCount];
		int[] y2 = new int[rectangleCount];
		in.readIntArrays(x1, y1, x2, y2);
		NavigableSet<Integer> xs = new TreeSet<Integer>();
		for (int i = 0; i < rectangleCount; i++) {
			x2[i]++;
			y2[i]++;
		}
		for (int i = 0; i < rectangleCount; i++) {
			xs.add(x1[i]);
			xs.add(x2[i]);
		}
		int index = 0;
		int[] orderedXs = new int[xs.size()];
		for (int x : xs)
			orderedXs[index++] = x;
		Map<Integer, Integer> convert = new HashMap<Integer, Integer>();
		for (int i = 0; i < orderedXs.length; i++)
			convert.put(orderedXs[i], i);
		for (int i = 0; i < rectangleCount; i++) {
			x1[i] = convert.get(x1[i]);
			x2[i] = convert.get(x2[i]);
		}
		List<TaskG.Event> events = new ArrayList<TaskG.Event>();
		for (int i = 0; i < rectangleCount; i++) {
			events.add(new TaskG.Event(x1[i], x2[i], y1[i], true));
			events.add(new TaskG.Event(x1[i], x2[i], y2[i], false));
		}
		Collections.sort(events, new Comparator<TaskG.Event>() {
			public int compare(TaskG.Event o1, TaskG.Event o2) {
				return o1.time - o2.time;
			}
		});
		IntervalTree tree = new IntervalTree(coverSize, orderedXs);
		long lastY = 0;
		long result = 0;
		for (TaskG.Event event : events) {
			result += (event.time - lastY) * tree.covered();
			lastY = event.time;
			if (event.add)
				tree.add(event.from, event.to);
			else
				tree.remove(event.from, event.to);
		}
		out.println("Case " + testNumber + ": " + result);
	}

	private static class Event {
		private final int from;
		private final int to;
		private final int time;
		private final boolean add;

		private Event(int from, int to, int time, boolean add) {
			this.from = from;
			this.to = to;
			this.time = time;
			this.add = add;
		}
	}
}

class IntervalTree {
	private int[] left, right, value;
	private int[][] length;

	IntervalTree(int k, int[] x) {
		int size = x.length - 1;
		left = new int[4 * size];
		right = new int[4 * size];
		value = new int[4 * size];
		length = new int[4 * size][k + 1];
		initTree(0, size, 0, x);
	}

	private void initTree(int left, int right, int root, int[] x) {
		this.left[root] = left;
		this.right[root] = right;
		length[root][0] = x[right] - x[left];
		if (right - left > 1) {
			initTree(left, (left + right) / 2, 2 * root + 1, x);
			initTree((left + right) / 2, right, 2 * root + 2, x);
		}
	}

	public void add(int left, int right) {
		add(left, right, 0);
	}

	private void add(int left, int right, int root) {
		if (this.left[root] >= right || this.right[root] <= left)
			return;
		if (this.left[root] >= left && this.right[root] <= right) {
			value[root]++;
			if (this.right[root] - this.left[root] == 1) {
				length[root][Math.min(length[root].length - 1, value[root])] = length[root][0];
				return;
			}
			for (int i = value[root]; i < length[root].length; i++)
				length[root][i] = length[2 * root + 1][i - value[root]] + length[2 * root + 2][i - value[root]];
			return;
		}
		add(left, right, 2 * root + 1);
		add(left, right, 2 * root + 2);
		for (int i = value[root] + 1; i < length[root].length; i++)
			length[root][i] = length[2 * root + 1][i - value[root]] + length[2 * root + 2][i - value[root]];
	}

	public void remove(int left, int right) {
		remove(left, right, 0);
	}

	private void remove(int left, int right, int root) {
		if (this.left[root] >= right || this.right[root] <= left)
			return;
		if (this.left[root] >= left && this.right[root] <= right) {
			value[root]--;
			if (this.right[root] - this.left[root] == 1) {
				if (value[root] < length[root].length - 1)
					length[root][value[root] + 1] = 0;
				return;
			}
			for (int i = value[root]; i < length[root].length; i++)
				length[root][i] = length[2 * root + 1][i - value[root]] + length[2 * root + 2][i - value[root]];
			return;
		}
		remove(left, right, 2 * root + 1);
		remove(left, right, 2 * root + 2);
		for (int i = value[root] + 1; i < length[root].length; i++)
			length[root][i] = length[2 * root + 1][i - value[root]] + length[2 * root + 2][i - value[root]];
	}

	public int covered() {
		return length[0][length[0].length - 1];
	}
}