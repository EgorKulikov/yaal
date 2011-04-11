package net.egork.timus;

import net.egork.arrays.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Task1178 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		final int[] x = new int[n];
		final int[] y = new int[n];
		in.readIntArrays(x, y);
		Integer[] order = ArrayUtils.generateOrder(n);
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (x[o1] != x[o2])
					return x[o1] - x[o2];
				return y[o1] - y[o2];
			}
		});
		for (int i = 0; i < n / 2; i++)
			out.println((order[2 * i] + 1) + " " + (order[2 * i + 1] + 1));
	}
}

