package net.egork.collections.intervaltree;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SumIntervalTree {
	private int[] left;
	private int[] right;
	private long[] value;
	private long[] delta;

	public SumIntervalTree(int size) {
		int arraysSize = Integer.highestOneBit(size) * 4;
		left = new int[arraysSize];
		right = new int[arraysSize];
		value = new long[arraysSize];
		delta = new long[arraysSize];
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
			return;
		}
		putSegment(left, right, value, 2 * root + 1);
		putSegment(left, right, value, 2 * root + 2);
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
		} else
			this.delta[root] += value;
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
			return value[root];
		return getSegment(left, right, 2 * root + 1) + getSegment(left, right, 2 * root + 2) + delta[root] *
			intersection(left, right, root);
	}
}
