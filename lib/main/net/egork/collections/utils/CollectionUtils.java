package net.egork.collections.utils;

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
}
