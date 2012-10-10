package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class FastDial {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] row = {3, 0, 0, 0, 1, 1, 1, 2, 2, 2};
		int[] column = {1, 0, 1, 2, 0, 1, 2, 0, 1, 2};
		double[][] distance = new double[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				distance[i][j] = Math.hypot(row[i] - row[j], column[i] - column[j]);
		}
		char[] digits = in.readString().toCharArray();
		double[] result = new double[10];
		double[] next = new double[10];
		digits[0] -= '0';
		for (int i = 1; i < digits.length; i++) {
			digits[i] -= '0';
			Arrays.fill(next, Double.POSITIVE_INFINITY);
			for (int j = 0; j < 10; j++) {
				next[j] = Math.min(next[j], result[j] + distance[digits[i - 1]][digits[i]]);
				next[digits[i - 1]] = Math.min(next[digits[i - 1]], result[j] + distance[j][digits[i]]);
			}
			double[] temp = next;
			next = result;
			result = temp;
		}
		out.printLine(CollectionUtils.minElement(Array.wrap(result)));
	}
}
