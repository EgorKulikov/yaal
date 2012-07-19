package net.egork;

import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int width = in.readInt();
		int length = in.readInt();
		int[] radii = IOUtils.readIntArray(in, count);
		Integer[] order = ListUtils.order(Array.wrap(radii), new ReverseComparator<Integer>());
		int[] x = new int[count];
		int[] y = new int[count];
		int lastX = 0;
		int lastY = 0;
		int lastSize = 0;
		int[] size = new int[count];
		boolean first = true;
		for (int i : order) {
			int curSize;
			if (radii[i] == 1)
				curSize = 1;
			else
				curSize = Integer.highestOneBit(radii[i] - 1) * 2;
			size[i] = curSize;
			if (lastX + lastSize + curSize > width) {
				lastY += lastSize + curSize;
				lastX = 0;
				for (int j : order) {
					if (i == j)
						break;
					if (Math.abs(lastY - y[j]) < size[j] + curSize)
						lastX = Math.max(lastX, x[j] + size[j] + curSize);
				}
			} else if (!first)
				lastX += lastSize + curSize;
			first = false;
			x[i] = lastX;
			y[i] = lastY;
			lastSize = curSize;
		}
		out.print("Case #" + testNumber + ":");
		for (int i = 0; i < count; i++)
			out.print(" " + x[i], y[i]);
		out.printLine();
	}
}
