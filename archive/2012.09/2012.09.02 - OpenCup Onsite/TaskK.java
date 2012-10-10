package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long canDig = in.readLong();
		int[] height = new int[count];
		for (int i = 0; i < count; i++)
			height[i] = in.readInt();
		int left = 0;
		int right = (int) 1e9;
		int[] copyHeight = new int[count];
		while (left < right) {
			System.arraycopy(height, 0, copyHeight, 0, count);
			int middle = (left + right) >> 1;
			if (can(copyHeight, middle, canDig) != -1)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(can(height, left, canDig) + 1, left);
	}

	long[] required;

	private int can(int[] height, int shift, long canDig) {
		int count = height.length;
		if (required == null)
			required = new long[count];
		for (int i = 1; i < count; i++) {
			int here = Math.max(0, height[i] - height[i - 1] - shift);
			height[i] -= here;
			canDig -= here;
		}
		for (int i = count - 2; i >= 0; i--) {
			int here = Math.max(0, height[i] - height[i + 1] - shift);
			height[i] -= here;
			canDig -= here;
		}
		if (canDig < 0)
			return -1;
		int to = 0;
		long sum = 0;
		Arrays.fill(required, 0);
		for (int i = 0; i < count; i++) {
			if (to < i) {
				if (sum != 0)
					throw new RuntimeException();
				to = i;
			}
			while (to < count && height[to] > (long)(to - i) * shift) {
				sum += height[to];
				to++;
			}
			long maxSum = (long)(to - i) * (to - i - 1) * shift / 2;
			required[i] += sum - maxSum;
			if (sum < maxSum)
				throw new RuntimeException();
			sum -= height[i];
		}
		to = count - 1;
		sum = 0;
		for (int i = count - 1; i >= 0; i--) {
			if (to > i) {
				if (sum != 0)
					throw new RuntimeException();
				to = i;
			}
			while (to >= 0 && height[to] > (long)(i - to) * shift) {
				sum += height[to];
				to--;
			}
			long maxSum = (long)(i - to) * (i - to - 1) * shift / 2;
			required[i] += sum - maxSum;
			if (sum < maxSum)
				throw new RuntimeException();
			sum -= height[i];
		}
		for (int i = 0; i < count; i++) {
			if (required[i] - height[i] <= canDig)
				return i;
		}
		return -1;
	}
}
