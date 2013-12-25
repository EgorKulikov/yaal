package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] radius = IOUtils.readIntArray(in, count);
		int[] reverse = new int[2 * count];
		for (int i = 0; i < count; i++)
			reverse[i] = radius[count - i - 1];
		System.arraycopy(reverse, 0, reverse, count, count);
		int[][] time = new int[Integer.bitCount(Integer.highestOneBit(count) - 1) + 1][];
		time[0] = new int[2 * count];
		for (int i = 0; i < time[0].length; i++)
			time[0][i] = i + reverse[i];
		IntervalTree[] trees = new IntervalTree[time.length];
		for (int i = 1; i < time.length; i++) {
			IntervalTree tree = new IntervalTree(time[i - 1]);
			trees[i - 1] = tree;
			time[i] = new int[2 * count];
			for (int j = 0; j < time[i].length; j++)
				time[i][j] = tree.query(j, time[i - 1][j]);
		}
		trees[trees.length - 1] = new IntervalTree(time[time.length- 1]);
		long answer = 0;
		for (int i = 0; i < count; i++) {
			int index = time.length - 1;
			int current = i;
			int target = count - 1 + i;
			while (index >= 0) {
				int queryResult = trees[index].query(i, current);
				if (queryResult >= target)
					index--;
				else {
					current = queryResult;
					answer += 1 << index;
					index--;
				}
			}
			answer++;
		}
		out.printLine(answer);
	}
}

class IntervalTree {
	protected int size;
	protected int[] value;

	protected IntervalTree(int[] array) {
		this.size = array.length;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		value = new int[nodeCount];
		init(array);
	}

	public void init(int[] array) {
		init(0, 0, size - 1, array);
	}

	private void init(int root, int left, int right, int[] array) {
		if (left == right)
			value[root] = array[left];
		else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, array);
			init(2 * root + 2, middle + 1, right, array);
			value[root] = Math.max(value[2 * root + 1], value[2 * root + 2]);
		}
	}

	public int query(int from, int to) {
		return query(0, 0, size - 1, from, to);
	}

	private int query(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return 0;
		if (left >= from && right <= to)
			return value[root];
		int middle = (left + right) >> 1;
		int leftResult = query(2 * root + 1, left, middle, from, to);
		int rightResult = query(2 * root + 2, middle + 1, right, from, to);
		return Math.max(leftResult, rightResult);
	}
}
