import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int roadCount = in.readInt();
		int raceCount = in.readInt();
		int[] cost = IOUtils.readIntArray(in, roadCount);
		int[] start = new int[raceCount];
		int[] finish = new int[raceCount];
		int[] prize = new int[raceCount];
		IOUtils.readIntArrays(in, start, finish, prize);
		int[] count = new int[roadCount];
		for (int i = 0; i < raceCount; i++)
			finish[i]--;
		int[][] races = GraphUtils.buildOrientedGraph(roadCount, finish, start);
		long answer = 0;
		SumIntervalTree tree = new SumIntervalTree(roadCount);
		for (int i = 0; i < roadCount; i++) {
			tree.put(i, answer);
			tree.putSegment(0, i + 1, -cost[i]);
			for (int j : races[i])
				tree.putSegment(0, start[j], prize[j]);
			answer = Math.max(answer, tree.getSegment(0, i + 1));
		}
		out.println(answer);
	}
}

class SumIntervalTree {
	private int[] left;
	private int[] right;
	private long[] value;
	private long[] delta;
	private long[] max;

	public SumIntervalTree(int size) {
		int arraysSize = Math.max(1, Integer.highestOneBit(size) << 2);
		left = new int[arraysSize];
		right = new int[arraysSize];
		value = new long[arraysSize];
		delta = new long[arraysSize];
		max = new long[arraysSize];
		initTree(0, size, 0);
	}

	private void initTree(int left, int right, int root) {
		this.left[root] = left;
		this.right[root] = right;
		if (right - left > 1) {
			initTree(left, (left + right + 1) / 2, 2 * root + 1);
			initTree((left + right + 1) / 2, right, 2 * root + 2);
		}
	}

	public void putSegment(int left, int right, long value) {
		putSegment(left, right, value, 0);
	}

	private void putSegment(int left, int right, long value, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return;
		this.value[root] += value * intersection(left, right, root);
		if (left <= this.left[root] && right >= this.right[root]) {
			this.delta[root] += value;
			max[root] += value;
			return;
		}
		putSegment(left, right, value, 2 * root + 1);
		putSegment(left, right, value, 2 * root + 2);
		max[root] = Math.max(max[2 * root + 1], max[2 * root + 2]) + delta[root];
	}

	private int intersection(int left, int right, int root) {
		return Math.min(right, this.right[root]) - Math.max(left, this.left[root]);
	}

	public void put(int position, long value) {
		put(position, value, 0);
	}

	private void put(int position, long value, int root) {
		if (left[root] > position || right[root] <= position)
			return;
		this.value[root] += value;
		if (right[root] - left[root] > 1) {
			put(position, value, 2 * root + 1);
			put(position, value, 2 * root + 2);
			max[root] = Math.max(max[2 * root + 1], max[2 * root + 2]) + delta[root];
		} else {
			this.delta[root] += value;
			max[root] = delta[root];
		}
	}

	public long get(int position) {
		return get(position, 0);
	}

	private long get(int position, int root) {
		if (position >= right[root] || position < left[root])
			return 0;
		if (right[root] - left[root] == 1)
			return value[root];
		return delta[root] + get(position, 2 * root + 1) + get(position, 2 * root + 2);
	}

	public long getSegment(int left, int right) {
		return getSegment(left, right, 0);
	}

	private long getSegment(int left, int right, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return 0;
		if (left <= this.left[root] && right >= this.right[root])
			return max[root];
		return Math.max(getSegment(left, right, 2 * root + 1), getSegment(left, right, 2 * root + 2)) + delta[root];
	}

}

