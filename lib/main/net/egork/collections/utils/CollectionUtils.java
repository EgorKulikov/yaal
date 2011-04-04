package net.egork.collections.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class CollectionUtils {
	public static Integer[] generateOrder(int size) {
		Integer[] order = new Integer[size];
		for (int i = 0; i < size; i++)
			order[i] = i;
		return order;
	}

	public static int[] toArray(List<Integer> list) {
		int[] array = new int[list.size()];
		int index = 0;
		for (int element : list)
			array[index++] = element;
		return array;
	}

	public static int minIndex(long[] array) {
		if (array.length == 0)
			return -1;
		long minimalValue = Long.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < minimalValue) {
				minimalValue = array[i];
				index = i;
			}
		}
		return index;
	}

	public static List<Integer> interval(int from, int to) {
		List<Integer> result = new ArrayList<Integer>(Math.max(from, to) - Math.min(from, to) + 1);
		if (to > from) {
			for (int i = from; i <= to; i++)
				result.add(i);
		} else {
			for (int i = from; i >= to; i--)
				result.add(i);
		}
		return result;
	}

	public static int maxIndex(int[] array) {
		if (array.length == 0)
			return -1;
		int maximalValue = Integer.MIN_VALUE;
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > maximalValue) {
				maximalValue = array[i];
				index = i;
			}
		}
		return index;
	}
}
