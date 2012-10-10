package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] parent = new int[count - 1];
		int[] wage = new int[count];
		wage[0] = in.readInt();
		for (int i = 1; i < count; i++) {
			wage[i] = in.readInt();
			parent[i - 1] = in.readInt() - 1;
		}
		int[] array = new int[count - 1];
		for (int i = 0; i < count - 1; i++)
			array[i] = i + 1;
		int[][] graph = GraphUtils.buildSimpleGraph(count, array, parent);
		int[] order = new int[count];
		int[] start = new int[count];
		int[] end = new int[count];
		build(0, -1, 0, order, start, end, graph);
		int[] orderedWage = new int[count];
		for (int i = 0; i < count; i++)
			orderedWage[order[i]] = wage[i];
		IntervalTree tree = new IntervalTree(orderedWage);
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'p') {
				int index = in.readInt() - 1;
				int delta = in.readInt();
				tree.putSegment(start[index], end[index], delta);
			} else
				out.printLine(tree.get(order[in.readInt() - 1]));
		}
	}

	private int build(int current, int last, int index, int[] order, int[] start, int[] end, int[][] graph) {
		order[current] = index++;
		start[current] = index;
		for (int i : graph[current]) {
			if (i != last)
				index = build(i, current, index, order, start, end, graph);
		}
		return end[current] = index;
	}
}

class IntervalTree {
	private int[] left;
	private int[] right;
	private long[] value;

	public IntervalTree(int[] initial) {
		int arraysSize = Math.max(1, Integer.highestOneBit(initial.length) << 2);
		left = new int[arraysSize];
		right = new int[arraysSize];
		value = new long[arraysSize];
		initTree(0, initial.length, 0, initial);
	}

	private void initTree(int left, int right, int root, int[] initial) {
		this.left[root] = left;
		this.right[root] = right;
		if (right - left > 1) {
			initTree(left, (left + right + 1) / 2, 2 * root + 1, initial);
			initTree((left + right + 1) / 2, right, 2 * root + 2, initial);
		} else {
			this.value[root] = initial[left];
		}
	}

	public void putSegment(int left, int right, int value) {
		putSegment(left, right, value, 0);
	}

	private void putSegment(int left, int right, int value, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return;
		if (left <= this.left[root] && right >= this.right[root]) {
			this.value[root] += value;
			return;
		}
		putSegment(left, right, value, 2 * root + 1);
		putSegment(left, right, value, 2 * root + 2);
	}

	public long get(int position) {
		return get(position, 0);
	}

	private long get(int position, int root) {
		if (position >= right[root] || position < left[root])
			return 0;
		if (right[root] - left[root] == 1)
			return value[root];
		return value[root] + get(position, 2 * root + 1) + get(position, 2 * root + 2);
	}
}
