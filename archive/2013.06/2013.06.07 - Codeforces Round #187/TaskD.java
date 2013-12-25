package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] x = new long[count];
		long[] y = new long[count];
		IOUtils.readLongArrays(in, x, y);
		for (int i = 0; i < count; i++) {
			long xx = x[i] + y[i];
			long yy = x[i] - y[i];
			x[i] = xx;
			y[i] = yy;
		}
		int[] order = ArrayUtils.order(x);
		long[] maxFromStart = new long[count + 1];
		long[] minFromStart = new long[count + 1];
		maxFromStart[0] = Long.MIN_VALUE;
		minFromStart[0] = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		long min = Long.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			max = Math.max(max, y[order[i]]);
			min = Math.min(min, y[order[i]]);
			maxFromStart[i + 1] = max;
			minFromStart[i + 1] = min;
		}
		long[] maxFromEnd = new long[count + 1];
		long[] minFromEnd = new long[count + 1];
		maxFromEnd[count] = max = Long.MIN_VALUE;
		minFromEnd[count] = min = Long.MAX_VALUE;
		for (int i = count - 1; i >= 0; i--) {
			max = Math.max(max, y[order[i]]);
			min = Math.min(min, y[order[i]]);
			maxFromEnd[i] = max;
			minFromEnd[i] = min;
		}
		long left = 0;
		long right = (long) 2e9;
		while (left < right) {
			long middle = (left + right) >> 1;
			if (maxFromStart[count] - minFromStart[count] <= middle) {
				right = middle;
				continue;
			}
			int j = 0;
			boolean found = false;
			for (int i = 0; i < count; i++) {
				while (x[order[i]] - x[order[j]] > middle)
					j++;
				if (j != 0 && maxFromStart[j] - minFromStart[j] > middle)
					break;
				if (j == 0 && i == count - 1 || Math.max(maxFromStart[j], maxFromEnd[i + 1]) - Math.min(minFromStart[j], minFromEnd[i + 1]) <= middle) {
					found = true;
					break;
				}
			}
			if (found)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine((left / 2) + "." + (left % 2 * 5));
	}
}
