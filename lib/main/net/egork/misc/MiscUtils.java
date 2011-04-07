package net.egork.misc;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class MiscUtils {
	public static final int[] DX_4_CONNECTED = {1, 0, -1, 0};
	public static final int[] DY_4_CONNECTED = {0, -1, 0, 1};

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
}
