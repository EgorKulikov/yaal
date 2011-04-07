package net.egork.arrays;

import net.egork.collections.intervaltree.SumIntervalTree;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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

	public static<T extends Comparable<T>> int minIndex(Array<T> array) {
		if (array.size() == 0)
			return -1;
		T minimalValue = array.get(0);
		int index = 0;
		for (int i = 1; i < array.size(); i++) {
			T element = array.get(i);
			if (element.compareTo(minimalValue) < 0) {
				minimalValue = element;
				index = i;
			}
		}
		return index;
	}

	public static<T extends Comparable<T>> int maxIndex(Array<T> array) {
		if (array.size() == 0)
			return -1;
		T maximalValue = array.get(0);
		int index = 0;
		for (int i = 1; i < array.size(); i++) {
			T element = array.get(i);
			if (element.compareTo(maximalValue) > 0) {
				maximalValue = element;
				index = i;
			}
		}
		return index;
	}

	public static<T> void fill(Array<T> array, T value) {
		for (int i = 0; i < array.size(); i++)
			array.set(i, value);			
	}

	public static void fill(long[][] array, long value) {
		for (long[] row : array)
			Arrays.fill(row, value);
	}

	public static void fillColumn(long[][] array, int index, long value) {
		for (long[] row : array)
			row[index] = value;
	}

	public static void fillColumn(int[][] array, int index, int value) {
		for (int[] row : array)
			row[index] = value;
	}

	public static void fill(int[][] array, int value) {
		for (int[] row : array)
			Arrays.fill(row, value);
	}

	public static void fill(boolean[][] array, boolean value) {
		for (boolean[] row : array)
			Arrays.fill(row, value);
	}

	public static<T extends Comparable<T>> long countUnorderedPairs(final Array<T> array) {
		long result = 0;
		Integer[] order = generateOrder(array.size());
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return array.get(o1).compareTo(array.get(o2));
			}
		});
		SumIntervalTree tree = new SumIntervalTree(array.size());
		for (int i : order) {
			result += i - tree.getSegment(0, i);
			tree.put(i, 1);
		}
		return result;
	}

	public static<T extends Comparable<T>> boolean nextPermutation(Array<T> array) {
		for (int i = array.size() - 2; i >= 0; i--) {
			if (array.get(i).compareTo(array.get(i + 1)) < 0) {
				int index = i + 1;
				for (int j = i + 2; j < array.size(); j++) {
					if (array.get(i).compareTo(array.get(j)) >= 0)
						break;
					else
						index = j;
				}
				T temp = array.get(i);
				array.set(i, array.get(index));
				array.set(index, temp);
				Collections.sort(array.subList(i + 1));
				return true;
			}
		}
		return false;
	}

	public static long sumArray(int[] array) {
		long result = 0;
		for (int element : array)
			result += element;
		return result;
	}
}
