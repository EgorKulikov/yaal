package net.egork.misc;

import net.egork.collections.function.Function;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class MiscUtils {
	public static final int[] DX4 = {1, 0, -1, 0};
	public static final int[] DY4 = {0, -1, 0, 1};
	public static final int[] DX8 = {1, 1, 1, 0, -1, -1, -1, 0};
	public static final int[] DY8 = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static final int[] DX_KNIGHT = {2, 1, -1, -2, -2, -1, 1, 2};
    public static final int[] DY_KNIGHT = {1, 2, 2, 1, -1, -2, -2, -1};

    private static final String[] ROMAN_TOKENS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	private static final int[] ROMAN_VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

	public static long josephProblem(long n, int k) {
		if (n == 1)
			return 0;
		if (k == 1)
			return n - 1;
		if (k > n)
			return (josephProblem(n - 1, k) + k) % n;
		long count = n / k;
		long result = josephProblem(n - count, k);
		result -= n % k;
		if (result < 0)
			result += n;
		else
			result += result / (k - 1);
		return result;
	}

	public static boolean isValidCell(int row, int column, int rowCount, int columnCount) {
		return row >= 0 && row < rowCount && column >= 0 && column < columnCount;
	}

	public static IntList getPath(int[] last, int destination) {
		IntList path = new IntArrayList();
		while (destination != -1) {
			path.add(destination);
			destination = last[destination];
		}
		path.inPlaceReverse();
		return path;
	}

	public static IntList getPath(int[][] lastIndex, int[][] lastPathNumber, int destination, int pathNumber) {
		IntList path = new IntArrayList();
		while (destination != -1 || pathNumber != 0) {
			path.add(destination);
			int nextDestination = lastIndex[destination][pathNumber];
			pathNumber = lastPathNumber[destination][pathNumber];
			destination = nextDestination;
		}
		path.inPlaceReverse();
		return path;
	}

	public static long maximalRectangleSum(long[][] array) {
		int n = array.length;
		int m = array[0].length;
		long[][] partialSums = new long[n + 1][m + 1];
		for (int i = 0; i < n; i++) {
			long rowSum = 0;
			for (int j = 0; j < m; j++) {
				rowSum += array[i][j];
				partialSums[i + 1][j + 1] = partialSums[i][j + 1] + rowSum;
			}
		}
		long result = Long.MIN_VALUE;
		for (int i = 0; i < m; i++) {
			for (int j = i; j < m; j++) {
				long minPartialSum = 0;
				for (int k = 1; k <= n; k++) {
					long current = partialSums[k][j + 1] - partialSums[k][i];
					result = Math.max(result, current - minPartialSum);
					minPartialSum = Math.min(minPartialSum, current);
				}
			}
		}
		return result;
	}

	public static int parseIP(String ip) {
		String[] components = ip.split("[.]");
		int result = 0;
		for (int i = 0; i < 4; i++)
			result += (1 << (24 - 8 * i)) * Integer.parseInt(components[i]);
		return result;
	}

	public static String buildIP(int mask) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			if (i != 0)
				result.append('.');
			result.append(mask >> (24 - 8 * i) & 255);
		}
		return result.toString();
	}

	public static long binarySearch(long from, long to, Function<Long, Boolean> function) {
		while (from < to) {
			long argument = from + (to - from) / 2;
			if (function.value(argument))
				to = argument;
			else
				from = argument + 1;
		}
		return from;
	}

	public static<T> boolean equals(T first, T second) {
		return first == null && second == null || first != null && first.equals(second);
	}

	public static boolean isVowel(char ch) {
		ch = Character.toUpperCase(ch);
		return ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' || ch == 'Y';
	}

	public static boolean isStrictVowel(char ch) {
		ch = Character.toUpperCase(ch);
		return ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
	}

	public static String convertToRoman(int number) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < ROMAN_TOKENS.length; i++) {
			while (number >= ROMAN_VALUES[i]) {
				number -= ROMAN_VALUES[i];
				result.append(ROMAN_TOKENS[i]);
			}
		}
		return result.toString();
	}

	public static int convertFromRoman(String number) {
		int result = 0;
		for (int i = 0; i < ROMAN_TOKENS.length; i++) {
			while (number.startsWith(ROMAN_TOKENS[i])) {
				number = number.substring(ROMAN_TOKENS[i].length());
				result += ROMAN_VALUES[i];
			}
		}
		return result;
	}

	public static int distance(int x1, int y1, int x2, int y2) {
		int dx = x1 - x2;
		int dy = y1 - y2;
		return dx * dx + dy * dy;
	}

	public static<T extends Comparable<T>> T min(T first, T second) {
		if (first.compareTo(second) <= 0)
			return first;
		return second;
	}

	public static<T extends Comparable<T>> T max(T first, T second) {
		if (first.compareTo(second) <= 0)
			return second;
		return first;
	}

	public static void decreaseByOne(int[]...arrays) {
		for (int[] array : arrays) {
			for (int i = 0; i < array.length; i++)
				array[i]--;
		}
	}

	public static int[] getIntArray(String s) {
		String[] tokens = s.split(" ");
		int[] result = new int[tokens.length];
		for (int i = 0; i < result.length; i++)
			result[i] = Integer.parseInt(tokens[i]);
		return result;
	}
}
