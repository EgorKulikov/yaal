package net.egork.collections;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ArrayUtils {
	public static Integer[] generateOrder(int size) {
		Integer[] order = new Integer[size];
		for (int i = 0; i < size; i++)
			order[i] = i;
		return order;
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

	public static long sumArray(int[] array) {
		long result = 0;
		for (int element : array)
			result += element;
		return result;
	}
}
