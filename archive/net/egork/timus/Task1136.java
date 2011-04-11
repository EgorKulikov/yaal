package net.egork.timus;

import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1136 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] order = in.readIntArray(n);
		go(order, 0, n - 1, new int[n]);
		IOUtils.printArray(order, out);
	}

	private void go(int[] order, int left, int right, int[] tempOrder) {
		if (left >= right)
			return;
		int splitPosition = -Arrays.binarySearch(order, left, right, order[right]) - 1;
		System.arraycopy(order, 0, tempOrder, 0, order.length);
		System.arraycopy(tempOrder, left, order, left + right - splitPosition, splitPosition - left);
		System.arraycopy(tempOrder, splitPosition, order, left, right - splitPosition);
		go(order, left, left + right - splitPosition - 1, tempOrder);
		go(order, left + right - splitPosition, right - 1, tempOrder);
	}
}

