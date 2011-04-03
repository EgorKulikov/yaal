package net.egork.collections.intervaltree.sumintervaltree;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SumIntervalTree {
	private int[] left;
	private int[] right;
	private long[] value;

	public SumIntervalTree(int size) {
		int arraysSize = Integer.highestOneBit(size) * 4;
		left = new int[arraysSize];
		right = new int[arraysSize];
		value = new long[arraysSize];
		initTree(0, size, 0);
	}

	private void initTree(int left, int right, int root) {
		this.left[root] = left;
		this.right[root] = right;
		value[root] = 0;
		if (right - left > 1) {
			initTree(left, (left + right + 1) / 2, 2 * root + 1);
			initTree((left + right + 1) / 2, right, 2 * root + 2);
		}
	}
	
	public void put(int left, int right, long value) {
		put(left, right, value, 0);
	}

	private void put(int left, int right, long value, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return;
		if (left <= this.left[root] && right >= this.right[root]) {
			this.value[root] += value;
			return;
		}
		put(left, right, value, 2 * root + 1);
		put(left, right, value, 2 * root + 2);
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
