package net.egork.collections.intervaltree;

/**
 * @author egor@egork.net
 */
public abstract class ReadOnlyLongIntervalTree {
	private int size;
	private long[] value;

	protected ReadOnlyLongIntervalTree(long[] array) {
		this.size = array.length;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		value = new long[nodeCount];
		init(0, 0, size - 1, array);
	}

	private void init(int root, int left, int right, long[] array) {
		if (left == right)
			value[root] = array[left];
		else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, array);
			init(2 * root + 2, middle + 1, right, array);
			value[root] = joinValue(value[2 * root + 1], value[2 * root + 2]);
		}
	}

	public long query(int from, int to) {
     return query(0, 0, size - 1, from, to);
 }

	private long query(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return neutralValue();
		if (left >= from && right <= to)
			return value[root];
		int middle = (left + right) >> 1;
		return joinValue(query(2 * root + 1, left, middle, from, to), query(2 * root + 2, middle + 1, right, from, to));
	}

	protected abstract long neutralValue();
	protected abstract long joinValue(long left, long right);
}
