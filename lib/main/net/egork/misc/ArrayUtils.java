package net.egork.misc;

import net.egork.collections.FenwickTree;
import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.list.CharArray;
import net.egork.generated.collections.list.DoubleArray;
import net.egork.generated.collections.list.IntArray;
import net.egork.generated.collections.list.LongArray;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ArrayUtils {
    public static void fill(short[][] array, short value) {
        for (short[] row : array) {
            Arrays.fill(row, value);
        }
    }

    public static void fill(long[][] array, long value) {
        for (long[] row : array) {
            Arrays.fill(row, value);
        }
    }

    public static void fill(double[][] array, double value) {
        for (double[] row : array) {
            Arrays.fill(row, value);
        }
    }

    public static void fill(double[][][] array, double value) {
        for (double[][] row : array) {
            fill(row, value);
        }
    }

    public static void fill(double[][][][] array, double value) {
        for (double[][][] row : array) {
            fill(row, value);
        }
    }

    public static void fill(double[][][][][] array, double value) {
        for (double[][][][] row : array) {
            fill(row, value);
        }
    }

    public static void fill(long[][][] array, long value) {
        for (long[][] row : array) {
            fill(row, value);
        }
    }

    public static void fill(long[][][][] array, long value) {
        for (long[][][] row : array) {
            fill(row, value);
        }
    }

    public static void fill(long[][][][][] array, long value) {
        for (long[][][][] row : array) {
            fill(row, value);
        }
    }

    public static void fillColumn(long[][] array, int index, long value) {
        for (long[] row : array) {
            row[index] = value;
        }
    }

    public static void fillColumn(int[][] array, int index, int value) {
        for (int[] row : array) {
            row[index] = value;
        }
    }

    public static void fill(int[][] array, int value) {
        for (int[] row : array) {
            Arrays.fill(row, value);
        }
    }

    public static void fill(boolean[][] array, boolean value) {
        for (boolean[] row : array) {
            Arrays.fill(row, value);
        }
    }

    public static void fill(boolean[][][] array, boolean value) {
        for (boolean[][] row : array) {
            fill(row, value);
        }
    }

    public static long sumArray(int[] array) {
        return new IntArray(array).sum();
    }

    public static int[] range(int from, int to) {
        return Range.range(from, to).toArray();
    }

    public static void fill(int[][][] array, int value) {
        for (int[][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static void fill(short[][][] array, short value) {
        for (short[][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static void fill(int[][][][] array, int value) {
        for (int[][][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static void fill(short[][][][] array, short value) {
        for (short[][][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static void fill(int[][][][][] array, int value) {
        for (int[][][][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static void fill(short[][][][][] array, short value) {
        for (short[][][][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static void fill(int[][][][][][] array, int value) {
        for (int[][][][][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static void fill(short[][][][][][] array, short value) {
        for (short[][][][][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static void fill(int[][][][][][][] array, int value) {
        for (int[][][][][][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static void fill(short[][][][][][][] array, short value) {
        for (short[][][][][][] subArray : array) {
            fill(subArray, value);
        }
    }

    public static <T> void fill(T[][] array, T value) {
        for (T[] row : array) {
            Arrays.fill(row, value);
        }
    }

    public static void fill(char[][] array, char value) {
        for (char[] row : array) {
            Arrays.fill(row, value);
        }
    }

    public static void fill(byte[][] array, byte value) {
        for (byte[] row : array) {
            Arrays.fill(row, value);
        }
    }

    public static void fill(byte[][][] array, byte value) {
        for (byte[][] row : array) {
            fill(row, value);
        }
    }

    public static void fill(byte[][][][] array, byte value) {
        for (byte[][][] row : array) {
            fill(row, value);
        }
    }

    public static long multiply(int[] first, int[] second) {
        long result = 0;
        for (int i = 0; i < first.length; i++) {
            result += (long) first[i] * second[i];
        }
        return result;
    }

    public static int[] createOrder(int size) {
        return range(0, size);
    }

    public static int[] sort(int[] array) {
        return sort(array, IntComparator.DEFAULT);
    }

    public static int[] sort(int[] array, IntComparator comparator) {
        return sort(array, 0, array.length, comparator);
    }

    public static int[] sort(int[] array, int from, int to, IntComparator comparator) {
        if (from == 0 && to == array.length) {
            new IntArray(array).sort(comparator);
        } else {
            new IntArray(array).subList(from, to).sort(comparator);
        }
        return array;
    }

    public static int[] order(final int[] array) {
        return sort(createOrder(array.length), new IntComparator() {
            public int compare(int first, int second) {
                if (array[first] < array[second]) {
                    return -1;
                }
                if (array[first] > array[second]) {
                    return 1;
                }
                return 0;
            }
        });
    }

    public static int[] order(final long[] array) {
        return sort(createOrder(array.length), new IntComparator() {
            public int compare(int first, int second) {
                if (array[first] < array[second]) {
                    return -1;
                }
                if (array[first] > array[second]) {
                    return 1;
                }
                return 0;
            }
        });
    }

    public static int[] unique(int[] array) {
        return new IntArray(array).unique().toArray();
    }

    public static long[] unique(long[] array) {
        return new LongArray(array).unique().toArray();
    }

    public static char[] unique(char[] array) {
        return new CharArray(array).unique().toArray();
    }

    public static int maxElement(int[] array) {
        return new IntArray(array).max();
    }

    public static int maxElement(int[] array, int from, int to) {
        return new IntArray(array).subList(from, to).max();
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
        for (int i = 0; i < permutation.length; i++) {
            result[permutation[i]] = i;
        }
        return result;
    }

    public static void reverse(int[] array) {
        new IntArray(array).inPlaceReverse();
    }

    public static void reverse(boolean[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            boolean temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(long[] array) {
        new LongArray(array).inPlaceReverse();
    }

    public static void reverse(char[] array) {
        new CharArray(array).inPlaceReverse();
    }

    public static int minPosition(int[] array) {
        return new IntArray(array).minIndex();
    }

    public static int minPosition(long[] array) {
        return new LongArray(array).minIndex();
    }

    public static int maxPosition(int[] array) {
        return new IntArray(array).maxIndex();
    }

    public static int maxPosition(double[] array) {
        return new DoubleArray(array).maxIndex();
    }

    public static int minPosition(int[] array, int from, int to) {
        return new IntArray(array).subList(from, to).minIndex() + from;
    }

    public static int maxPosition(int[] array, int from, int to) {
        return new IntArray(array).subList(from, to).maxIndex() + from;
    }

    public static int[] multiplyPermutations(int[] first, int[] second) {
        int count = first.length;
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = first[second[i]];
        }
        return result;
    }

    public static int[] compress(int[]... arrays) {
        int totalLength = 0;
        for (int[] array : arrays) {
            totalLength += array.length;
        }
        int[] all = new int[totalLength];
        int delta = 0;
        for (int[] array : arrays) {
            System.arraycopy(array, 0, all, delta, array.length);
            delta += array.length;
        }
        sort(all, IntComparator.DEFAULT);
        all = unique(all);
        for (int[] array : arrays) {
            for (int i = 0; i < array.length; i++) {
                array[i] = Arrays.binarySearch(all, array[i]);
            }
        }
        return all;
    }

    public static long[] compress(long[]... arrays) {
        int totalLength = 0;
        for (long[] array : arrays) {
            totalLength += array.length;
        }
        long[] all = new long[totalLength];
        int delta = 0;
        for (long[] array : arrays) {
            System.arraycopy(array, 0, all, delta, array.length);
            delta += array.length;
        }
        new LongArray(all).sort();
        all = unique(all);
        for (long[] array : arrays) {
            for (int i = 0; i < array.length; i++) {
                array[i] = Arrays.binarySearch(all, array[i]);
            }
        }
        return all;
    }

    public static int minElement(int[] array) {
        return new IntArray(array).min();
    }

    public static long[] partialSums(int[] array) {
        long[] result = new long[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            result[i + 1] = result[i] + array[i];
        }
        return result;
    }

    public static void orderBy(int[] base, int[]... arrays) {
        int[] order = ArrayUtils.order(base);
        order(order, base);
        for (int[] array : arrays) {
            order(order, array);
        }
    }

    public static void orderBy(long[] base, long[]... arrays) {
        int[] order = ArrayUtils.order(base);
        order(order, base);
        for (long[] array : arrays) {
            order(order, array);
        }
    }

    public static void order(int[] order, int[] array) {
        int[] tempInt = new int[order.length];
        for (int i = 0; i < order.length; i++) {
            tempInt[i] = array[order[i]];
        }
        System.arraycopy(tempInt, 0, array, 0, array.length);
    }

    public static void order(int[] order, long[] array) {
        long[] tempLong = new long[order.length];
        for (int i = 0; i < order.length; i++) {
            tempLong[i] = array[order[i]];
        }
        System.arraycopy(tempLong, 0, array, 0, array.length);
    }

    public static long[] asLong(int[] array) {
        long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static int count(int[] array, int value) {
        return new IntArray(array).count(value);
    }

    public static int count(long[] array, long value) {
        return new LongArray(array).count(value);
    }

    public static int count(double[] array, double value) {
        return new DoubleArray(array).count(value);
    }

    public static int count(char[] array, char value) {
        return new CharArray(array).count(value);
    }

    public static int count(boolean[] array, boolean value) {
        int result = 0;
        for (boolean i : array) {
            if (i == value) {
                result++;
            }
        }
        return result;
    }

    public static int[] merge(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int firstIndex = 0;
        int secondIndex = 0;
        int index = 0;
        while (firstIndex < first.length && secondIndex < second.length) {
            if (first[firstIndex] < second[secondIndex]) {
                result[index++] = first[firstIndex++];
            } else {
                result[index++] = second[secondIndex++];
            }
        }
        System.arraycopy(first, firstIndex, result, index, first.length - firstIndex);
        System.arraycopy(second, secondIndex, result, index, second.length - secondIndex);
        return result;
    }

    public static boolean nextPermutation(int[] array) {
        return new IntArray(array).nextPermutation();
    }

    public static <V> void reverse(V[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            V temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static IntComparator compareBy(final int[]... arrays) {
        return new IntComparator() {
            public int compare(int first, int second) {
                for (int[] array : arrays) {
                    if (array[first] != array[second]) {
                        return Integer.compare(array[first], array[second]);
                    }
                }
                return 0;
            }
        };
    }

    public static long minElement(long[] array) {
        return new LongArray(array).min();
    }

    public static long maxElement(long[] array) {
        return new LongArray(array).max();
    }

    public static int maxPosition(long[] array) {
        return new LongArray(array).maxIndex();
    }

    public static int maxPosition(long[] array, int from, int to) {
        return new LongArray(array).subList(from, to).maxIndex() + from;
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

    public static <T> T[] createArray(int count, T value) {
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
        return new IntArray(array).find(value);
    }

    public static int find(char[] array, char value) {
        return new CharArray(array).find(value);
    }

    public static boolean getOddity(int[] p) {
        FenwickTree tree = new FenwickTree(p.length);
        long total = 0;
        for (int i : p) {
            total += i - tree.get(0, i);
            tree.add(i, 1);
        }
        return total % 2 == 1;
    }

    public static long[] concatenate(long[] arr1, long[] arr2) {
        long[] result = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }

    public static char[] concatenate(char[] arr1, char[] arr2) {
        char[] result = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }

    public static int[] concatenate(int[] arr1, int[] arr2) {
        int[] result = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }
}
