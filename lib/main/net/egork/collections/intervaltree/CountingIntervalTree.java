package net.egork.collections.intervaltree;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;

import java.util.Arrays;

public class CountingIntervalTree {
	protected int size;
	protected int[][] value;

	protected CountingIntervalTree(int[] array) {
		this.size = array.length;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		value = new int[nodeCount][];
		init(array);
	}

	public void init(int[] array) {
		init(0, 0, size - 1, array);
	}

	private void init(int root, int left, int right, int[] array) {
		if (left == right) {
			value[root] = new int[]{array[left] * 2};
		} else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, array);
			init(2 * root + 2, middle + 1, right, array);
			value[root] = new int[right - left + 1];
			System.arraycopy(value[2 * root + 1], 0, value[root], 0, value[2 * root + 1].length);
			System.arraycopy(value[2 * root + 2], 0, value[root], value[2 * root + 1].length, value[2 * root + 2].length);
			if (value[root].length != value[2 * root + 1].length + value[2 * root + 2].length)
				throw new RuntimeException();
			ArrayUtils.sort(value[root], IntComparator.DEFAULT);
		}
	}

	public int query(int from, int to, int value) {
		return query(0, 0, size - 1, from, to, value);
	}

	private int query(int root, int left, int right, int from, int to, int value) {
		if (left > to || right < from)
			return 0;
		if (left >= from && right <= to)
			return Arrays.binarySearch(this.value[root], 2 * value - 1) - Arrays.binarySearch(this.value[root], 2 * value + 1);
		int middle = (left + right) >> 1;
		return query(2 * root + 1, left, middle, from, to, value) + query(2 * root + 2, middle + 1, right, from, to, value);
	}
}
