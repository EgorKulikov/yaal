package net.egork.arrays;

import net.egork.collections.CollectionUtils;
import net.egork.collections.intervaltree.SumIntervalTree;

import java.util.*;

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
		return array.indexOf(CollectionUtils.minElement(array));
	}

	public static<T extends Comparable<T>> int maxIndex(Array<T> array) {
		return array.indexOf(CollectionUtils.maxElement(array));
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
				sort(array.subArray(i + 1));
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

	public static int[] range(int from, int to) {
		int[] result = new int[Math.max(from, to) - Math.min(from, to) + 1];
		int index = 0;
		if (to > from) {
			for (int i = from; i <= to; i++)
				result[index++] = i;
		} else {
			for (int i = from; i >= to; i--)
				result[index++] = i;
		}
		return result;
	}

	@SuppressWarnings({"unchecked"})
	public static<T extends Comparable<T>> Array<T> sort(Array<T> array) {
		Object underlying = array.underlying;
		if (underlying instanceof char[])
			Arrays.sort((char[]) underlying, array.from, array.to);
		else if (underlying instanceof int[])
			Arrays.sort((int[]) underlying, array.from, array.to);
		else if (underlying instanceof long[])
			Arrays.sort((long[]) underlying, array.from, array.to);
		else if (underlying instanceof Object[])
			Arrays.sort((T[]) underlying, array.from, array.to);
		else if (underlying instanceof List)
			Collections.sort(((List<T>) underlying).subList(array.from, array.to));
		else
			throw new IllegalArgumentException();
		return array;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	public static<T> Array<T> sort(Array<T> array, Comparator<? super T> comparator) {
		Object underlying = array.underlying;
		if (underlying instanceof char[]) {
			Character[] copy = new Character[array.size];
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				copy[i] = ((char[])underlying)[j];
			Arrays.sort(copy, (Comparator<? super Character>) comparator);
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				((char[])underlying)[j] = copy[i];
		} else if (underlying instanceof int[]) {
			Integer[] copy = new Integer[array.size];
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				copy[i] = ((int[])underlying)[j];
			Arrays.sort(copy, (Comparator<? super Integer>) comparator);
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				((int[])underlying)[j] = copy[i];
		} else if (underlying instanceof long[]) {
			Long[] copy = new Long[array.size];
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				copy[i] = ((long[])underlying)[j];
			Arrays.sort(copy, (Comparator<? super Long>) comparator);
			for (int i = 0, j = array.from; i < array.size; i++, j++)
				((long[])underlying)[j] = copy[i];
		} else if (underlying instanceof Object[])
			Arrays.sort((T[]) underlying, array.from, array.to, comparator);
		else if (underlying instanceof List)
			Collections.sort(((List<T>) underlying).subList(array.from, array.to), comparator);
		else
			throw new IllegalArgumentException();
		return array;
	}

	public static<T> boolean isSubSequence(Array<T> array, Array<T> sample) {
		int index = 0;
		for (T element : array) {
			if (element.equals(sample.get(index))) {
				if (++index == sample.size)
					return true;
			}
		}
		return false;
	}

	public static Integer[] order(int size, Comparator<Integer> comparator) {
		Integer[] order = generateOrder(size);
		Arrays.sort(order, comparator);
		return order;
	}

	public static<T extends Comparable> Integer[] order(final Array<T> array) {
		return order(array.size, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return array.get(o1).compareTo(array.get(o2));
			}
		});
	}

	public static<T> int count(Array<T> array, T sample) {
		int result = 0;
		for (T element : array) {
			if (element.equals(sample))
				result++;
		}
		return result;
	}
}
