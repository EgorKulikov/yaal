package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	double[] value;
	int[] last;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int[] x = new int[count];
		int[] beauty = new int[count];
		IOUtils.readIntArrays(in, x, beauty);
		double left = 0;
		double right = 1e6;
		last = new int[count + 1];
		value = new double[count + 1];
		for (int i = 0; i < 60; i++) {
			double mid = (left + right) / 2;
			if (solve(x, beauty, length, mid)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		solve(x, beauty, length, right);
		IntList answer = new IntArrayList();
		for (int i = count; i != 0; i = last[i]) {
			answer.add(i);
		}
		answer.inPlaceReverse();
		out.printLine(answer);
    }

	private boolean solve(int[] x, int[] beauty, int length, double mid) {
		Arrays.fill(value, Double.POSITIVE_INFINITY);
		value[0] = 0;
		for (int i = 1; i < value.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				double candidate = value[j] + Math.sqrt(Math.abs(x[i - 1] - (j > 0 ? x[j - 1] : 0) - length)) - beauty[i - 1] * mid;
				if (candidate < value[i]) {
					value[i] = candidate;
					last[i] = j;
				}
			}
		}
		return value[x.length] <= 0;
	}
}
