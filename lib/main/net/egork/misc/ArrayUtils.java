package net.egork.misc;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArray;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ArrayUtils {
	private static int[] tempInt = new int[0];
	private static long[] tempLong = new long[0];

	public static Integer[] generateOrder(int size) {
		Integer[] order = new Integer[size];
		for (int i = 0; i < size; i++)
			order[i] = i;
		return order;
	}

    public static void fill(short[][] array, short value) {
        for (short[] row : array)
            Arrays.fill(row, value);
    }

    public static void fill(long[][] array, long value) {
		for (long[] row : array)
			Arrays.fill(row, value);
	}

	public static void fill(double[][] array, double value) {
		for (double[] row : array)
			Arrays.fill(row, value);
	}

	public static void fill(double[][][] array, double value) {
		for (double[][] row : array)
			fill(row, value);
	}

	public static void fill(double[][][][] array, double value) {
		for (double[][][] row : array)
			fill(row, value);
	}

	public static void fill(double[][][][][] array, double value) {
		for (double[][][][] row : array)
			fill(row, value);
	}

	public static void fill(long[][][] array, long value) {
		for (long[][] row : array)
			fill(row, value);
	}

	public static void fill(long[][][][] array, long value) {
		for (long[][][] row : array)
			fill(row, value);
	}

	public static void fill(long[][][][][] array, long value) {
		for (long[][][][] row : array)
			fill(row, value);
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

    public static void fill(boolean[][][] array, boolean value) {
        for (boolean[][] row : array)
            fill(row, value);
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

	public static void fill(int[][][] array, int value) {
		for (int[][] subArray : array)
			fill(subArray, value);
	}

	public static void fill(short[][][] array, short value) {
		for (short[][] subArray : array)
			fill(subArray, value);
	}

	public static void fill(int[][][][] array, int value) {
		for (int[][][] subArray : array)
			fill(subArray, value);
	}

	public static void fill(short[][][][] array, short value) {
		for (short[][][] subArray : array)
			fill(subArray, value);
	}

	public static void fill(int[][][][][] array, int value) {
		for (int[][][][] subArray : array)
			fill(subArray, value);
	}

	public static void fill(short[][][][][] array, short value) {
		for (short[][][][] subArray : array)
			fill(subArray, value);
	}

	public static void fill(int[][][][][][] array, int value) {
		for (int[][][][][] subArray : array)
			fill(subArray, value);
	}

	public static void fill(short[][][][][][] array, short value) {
		for (short[][][][][] subArray : array)
			fill(subArray, value);
	}

	public static void fill(int[][][][][][][] array, int value) {
		for (int[][][][][][] subArray : array)
			fill(subArray, value);
	}

	public static void fill(short[][][][][][][] array, short value) {
		for (short[][][][][][] subArray : array)
			fill(subArray, value);
	}

	public static Integer[] order(int size, Comparator<Integer> comparator) {
		Integer[] order = generateOrder(size);
		Arrays.sort(order, comparator);
		return order;
	}

	public static <T> void fill(T[][] array, T value) {
		for (T[] row : array)
			Arrays.fill(row, value);
	}

	public static void fill(char[][] array, char value) {
		for (char[] row : array)
			Arrays.fill(row, value);
	}

	public static void fill(byte[][] array, byte value) {
		for (byte[] row : array)
			Arrays.fill(row, value);
	}

	public static void fill(byte[][][] array, byte value) {
		for (byte[][] row : array)
			fill(row, value);
	}

	public static void fill(byte[][][][] array, byte value) {
		for (byte[][][] row : array)
			fill(row, value);
	}

	public static long multiply(int[] first, int[] second) {
		long result = 0;
		for (int i = 0; i < first.length; i++)
			result += (long) first[i] * second[i];
		return result;
	}

	public static int[] createOrder(int size) {
		int[] order = new int[size];
		for (int i = 0; i < size; i++)
			order[i] = i;
		return order;
	}

	public static int[] sort(int[] array) {
		return sort(array, IntComparator.DEFAULT);
	}

	public static int[] sort(int[] array, IntComparator comparator) {
		return sort(array, 0, array.length, comparator);
	}

	public static int[] sort(int[] array, int from, int to, IntComparator comparator) {
		if (from == 0 && to == array.length)
			new IntArray(array).inPlaceSort(comparator);
		else
			new IntArray(array).subList(from, to).inPlaceSort(comparator);
		return array;
	}

	private static void ensureCapacityInt(int size) {
		if (tempInt.length >= size)
			return;
		size = Math.max(size, tempInt.length << 1);
		tempInt = new int[size];
	}

	private static void ensureCapacityLong(int size) {
		if (tempLong.length >= size)
			return;
		size = Math.max(size, tempLong.length << 1);
		tempLong = new long[size];
	}

	private static void sortImpl(int[] array, int from, int to, int[] temp, int fromTemp, int toTemp, IntComparator comparator) {
		if (to - from <= 1)
			return;
		int middle = (to - from) >> 1;
		int tempMiddle = fromTemp + middle;
		sortImpl(temp, fromTemp, tempMiddle, array, from, from + middle, comparator);
		sortImpl(temp, tempMiddle, toTemp, array, from + middle, to, comparator);
		int index = from;
		int index1 = fromTemp;
		int index2 = tempMiddle;
		while (index1 < tempMiddle && index2 < toTemp) {
			if (comparator.compare(temp[index1], temp[index2]) <= 0)
				array[index++] = temp[index1++];
			else
				array[index++] = temp[index2++];
		}
		if (index1 != tempMiddle)
			System.arraycopy(temp, index1, array, index, tempMiddle - index1);
		if (index2 != toTemp)
			System.arraycopy(temp, index2, array, index, toTemp - index2);
	}

	public static int[] order(final int[] array) {
		return sort(createOrder(array.length), new IntComparator() {
			public int compare(int first, int second) {
				if (array[first] < array[second])
					return -1;
				if (array[first] > array[second])
					return 1;
				return 0;
			}
		});
	}

	public static int[] order(final long[] array) {
		return sort(createOrder(array.length), new IntComparator() {
			public int compare(int first, int second) {
				if (array[first] < array[second])
					return -1;
				if (array[first] > array[second])
					return 1;
				return 0;
			}
		});
	}

	public static int[] unique(int[] array) {
		return unique(array, 0, array.length);
	}

	public static int[] unique(int[] array, int from, int to) {
		if (from == to)
			return new int[0];
		int count = 1;
		for (int i = from + 1; i < to; i++) {
			if (array[i] != array[i - 1])
				count++;
		}
		int[] result = new int[count];
		result[0] = array[from];
		int index = 1;
		for (int i = from + 1; i < to; i++) {
			if (array[i] != array[i - 1])
				result[index++] = array[i];
		}
		return result;
	}

    public static char[] unique(char[] array) {
        return unique(array, 0, array.length);
    }

    public static char[] unique(char[] array, int from, int to) {
        if (from == to)
            return new char[0];
        int count = 1;
        for (int i = from + 1; i < to; i++) {
            if (array[i] != array[i - 1])
                count++;
        }
        char[] result = new char[count];
        result[0] = array[from];
        int index = 1;
        for (int i = from + 1; i < to; i++) {
            if (array[i] != array[i - 1])
                result[index++] = array[i];
        }
        return result;
    }

    public static int maxElement(int[] array) {
		return maxElement(array, 0, array.length);
	}

	public static int maxElement(int[] array, int from, int to) {
		int result = Integer.MIN_VALUE;
		for (int i = from; i < to; i++)
			result = Math.max(result, array[i]);
		return result;
	}

	public static int[] order(final double[] array) {
		return sort(createOrder(array.length), new IntComparator() {
			public int compare(int first, int second) {
				return Double.compare(array[first], array[second]);
			}
		});
	}

	public static int[] reversePermutation(int[] permutation) {
		int[] result = new int[permutation.length];
		for (int i = 0; i < permutation.length; i++)
			result[permutation[i]] = i;
		return result;
	}

	public static void reverse(int[] array) {
		for (int i = 0, j = array.length - 1; i < j; i++, j--) {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

	public static void reverse(char[] array) {
		for (int i = 0, j = array.length - 1; i < j; i++, j--) {
			char temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

	private static long maxElement(long[] array, int from, int to) {
		long result = Long.MIN_VALUE;
		for (int i = from; i < to; i++)
			result = Math.max(result, array[i]);
		return result;
	}

	public static int minPosition(int[] array) {
		return minPosition(array, 0, array.length);
	}

	public static int maxPosition(int[] array) {
		return maxPosition(array, 0, array.length);
	}

	public static int minPosition(int[] array, int from, int to) {
        if (from >= to)
            return -1;
        int min = array[from];
        int result = from;
        for (int i = from + 1; i < to; i++) {
            if (array[i] < min) {
                min = array[i];
                result = i;
            }
        }
        return result;
    }

	public static int maxPosition(int[] array, int from, int to) {
		if (from >= to)
			return -1;
		int max = array[from];
		int result = from;
		for (int i = from + 1; i < to; i++) {
			if (array[i] > max) {
				max = array[i];
				result = i;
			}
		}
		return result;
	}

	public static int[] multiplyPermutations(int[] first, int[] second) {
		int count = first.length;
		int[] result = new int[count];
		for (int i = 0; i < count; i++) {
			result[i] = first[second[i]];
		}
		return result;
	}

	public static int[] compress(int[]...arrays) {
		int totalLength = 0;
		for (int[] array : arrays)
			totalLength += array.length;
		int[] all = new int[totalLength];
		int delta = 0;
		for (int[] array : arrays) {
			System.arraycopy(array, 0, all, delta, array.length);
			delta += array.length;
		}
		sort(all, IntComparator.DEFAULT);
		all = unique(all);
		for (int[] array : arrays) {
			for (int i = 0; i < array.length; i++)
				array[i] = Arrays.binarySearch(all, array[i]);
		}
		return all;
	}

	public static int minElement(int[] array) {
		return array[minPosition(array)];
	}

	public static long[] partialSums(int[] array) {
		long[] result = new long[array.length + 1];
		for (int i = 0; i < array.length; i++)
			result[i + 1] = result[i] + array[i];
		return result;
	}

	public static void orderBy(int[] base, int[]... arrays) {
		int[] order = ArrayUtils.order(base);
		order(order, base);
		for (int[] array : arrays)
			order(order, array);
	}

	public static void orderBy(long[] base, long[]... arrays) {
		int[] order = ArrayUtils.order(base);
		order(order, base);
		for (long[] array : arrays)
			order(order, array);
	}

	public static void order(int[] order, int[] array) {
		ensureCapacityInt(order.length);
		for (int i = 0; i < order.length; i++)
			tempInt[i] = array[order[i]];
		System.arraycopy(tempInt, 0, array, 0, array.length);
	}

	public static void order(int[] order, long[] array) {
		ensureCapacityLong(order.length);
		for (int i = 0; i < order.length; i++)
			tempLong[i] = array[order[i]];
		System.arraycopy(tempLong, 0, array, 0, array.length);
	}

	public static long[] asLong(int[] array) {
		long[] result = new long[array.length];
		for (int i = 0; i < array.length; i++)
			result[i] = array[i];
		return result;
	}

	public static int count(int[] array, int value) {
		int result = 0;
		for (int i : array) {
			if (i == value)
				result++;
		}
		return result;
	}

	public static int count(long[] array, long value) {
		int result = 0;
		for (long i : array) {
			if (i == value) {
				result++;
			}
		}
		return result;
	}

	public static int count(char[] array, char value) {
		int result = 0;
		for (char i : array) {
			if (i == value)
				result++;
		}
		return result;
	}

	public static int count(boolean[] array, boolean value) {
		int result = 0;
		for (boolean i : array) {
			if (i == value)
				result++;
		}
		return result;
	}

	public static int[] merge(int[] first, int[] second) {
		int[] result = new int[first.length + second.length];
		int firstIndex = 0;
		int secondIndex = 0;
		int index = 0;
		while (firstIndex < first.length && secondIndex < second.length) {
			if (first[firstIndex] < second[secondIndex])
				result[index++] = first[firstIndex++];
			else
				result[index++] = second[secondIndex++];
		}
		System.arraycopy(first, firstIndex, result, index, first.length - firstIndex);
		System.arraycopy(second, secondIndex, result, index, second.length - secondIndex);
		return result;
	}

	public static boolean nextPermutation(int[] array) {
		return new IntArray(array).nextPermutation();
	}

	public static <V>void reverse(V[] array) {
		for (int i = 0, j = array.length - 1; i < j; i++, j--) {
			V temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

	public static IntComparator compareBy(final int[]...arrays) {
		return new IntComparator() {
			public int compare(int first, int second) {
				for (int[] array : arrays) {
					if (array[first] != array[second])
						return Integer.compare(array[first], array[second]);
				}
				return 0;
			}
		};
	}

	public static long minElement(long[] array) {
		return array[minPosition(array)];
	}

	public static long maxElement(long[] array) {
		return array[maxPosition(array)];
	}

	public static int minPosition(long[] array) {
		return minPosition(array, 0, array.length);
	}

	public static int maxPosition(long[] array) {
		return maxPosition(array, 0, array.length);
	}

	public static int minPosition(long[] array, int from, int to) {
		if (from >= to)
			return -1;
		long min = array[from];
		int result = from;
		for (int i = from + 1; i < to; i++) {
			if (array[i] < min) {
				min = array[i];
				result = i;
			}
		}
		return result;
	}

	public static int maxPosition(long[] array, int from, int to) {
		if (from >= to)
			return -1;
		long max = array[from];
		int result = from;
		for (int i = from + 1; i < to; i++) {
			if (array[i] > max) {
				max = array[i];
				result = i;
			}
		}
		return result;
	}

	public static int[] createArray(int count, int value) {
		int[] array = new int[count];
		Arrays.fill(array, value);
		return array;
	}

	public static long[] createArray(int count, long value) {
		long[] array = new long[count];
		Arrays.fill(array, value);
		return array;
	}

	public static double[] createArray(int count, double value) {
		double[] array = new double[count];
		Arrays.fill(array, value);
		return array;
	}

	public static boolean[] createArray(int count, boolean value) {
		boolean[] array = new boolean[count];
		Arrays.fill(array, value);
		return array;
	}

	public static char[] createArray(int count, char value) {
		char[] array = new char[count];
		Arrays.fill(array, value);
		return array;
	}

	public static<T> T[] createArray(int count, T value) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(value.getClass(), count);
		Arrays.fill(array, value);
		return array;
	}

	public static long[][] partialSums(int[][] array) {
		int height = array.length;
		int width = array[0].length;
		long[][] result = new long[height + 1][width + 1];
		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++) {
				result[i][j] = result[i][j - 1] + result[i - 1][j] - result[i - 1][j - 1] + array[i - 1][j - 1];
			}
		}
		return result;
	}

	public static long[][] partialSums(long[][] array) {
		int height = array.length;
		int width = array[0].length;
		long[][] result = new long[height + 1][width + 1];
		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++) {
				result[i][j] = result[i][j - 1] + result[i - 1][j] - result[i - 1][j - 1] + array[i - 1][j - 1];
			}
		}
		return result;
	}

	public static int[][] transpose(int[][] array) {
		if (array.length == 0) {
			return new int[0][0];
		}
		int[][] result = new int[array[0].length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				result[j][i] = array[i][j];
			}
		}
		return result;
	}

	public static int find(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
