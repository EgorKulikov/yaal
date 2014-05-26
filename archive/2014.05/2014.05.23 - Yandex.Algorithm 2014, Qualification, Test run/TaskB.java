package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntPair;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] width = new int[count];
		final int[] height = new int[count];
		IOUtils.readIntArrays(in, width, height);
		Set<IntPair> present = new EHashSet<>();
		for (int i = 0; i < count; i++) {
			IntPair current = new IntPair(width[i], height[i]);
			if (present.contains(current)) {
				width[i] = current.second;
				height[i] = current.first;
			} else
				present.add(current);
		}
		present = new EHashSet<>();
		for (int i = 0; i < count; i++) {
			IntPair current = new IntPair(width[i], height[i]);
			if (present.contains(current)) {
				out.printLine("Chaos");
				return;
			}
			present.add(current);
		}
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				if (width[first] != width[second])
					return width[second] - width[first];
				return height[second] - height[first];
			}
		});
		for (int i : order)
			out.printLine(width[i], height[i]);
    }
}
