package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] sizes = IOUtils.readIntArray(in, count);
		ArrayUtils.sort(sizes, IntComparator.DEFAULT);
		int left = 0;
		int right = count >> 1;
		while (left < right) {
			int middle = (left + right + 1) >> 1;
			boolean good = true;
			for (int j = 0; j < middle && good; j++) {
				if (sizes[j] * 2 > sizes[count - middle + j])
					good = false;
			}
			if (good)
				left = middle;
			else
				right = middle - 1;
		}
		out.printLine(count - left);
    }
}
