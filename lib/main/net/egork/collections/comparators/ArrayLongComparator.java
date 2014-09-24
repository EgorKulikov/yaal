package net.egork.collections.comparators;

import java.util.Comparator;

/**
 * @author egor@egork.net
 */
public class ArrayLongComparator implements Comparator<Integer> {
	private final long[] array;

	public ArrayLongComparator(long[] array) {
		this.array = array;
	}

	@Override
	public int compare(Integer o1, Integer o2) {
		if (array[o1] != array[o2]) {
			return Long.compare(array[o1], array[o2]);
		}
		return o1 - o2;
	}
}
