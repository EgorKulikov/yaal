package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] width = new int[count];
		int[] height = new int[count];
		IOUtils.readIntArrays(in, width, height);
		int[] all = Arrays.copyOf(width, 2 * count);
		System.arraycopy(height, 0, all, count, count);
		Arrays.sort(all);
		long answer = Long.MAX_VALUE;
		for (int i : all) {
			long sum = 0;
			IntList rotatedDelta = new IntArrayList();
			int numRotated = 0;
			boolean good = true;
			for (int j = 0; j < count; j++) {
				if (height[j] > width[j]) {
					if (height[j] <= i) {
						sum += width[j];
					} else {
						if (width[j] <= i) {
							sum += height[j];
							numRotated++;
						} else {
							good = false;
							break;
						}
					}
				} else {
					if (width[j] <= i) {
						sum += height[j];
						numRotated++;
						rotatedDelta.add(width[j] - height[j]);
					} else {
						if (height[j] <= i) {
							sum += width[j];
						} else {
							good = false;
							break;
						}
					}
				}
			}
			if (!good || numRotated - rotatedDelta.size() > count / 2) {
				continue;
			}
			rotatedDelta.inPlaceSort();
			for (int j = 0; j < numRotated - count / 2; j++) {
				sum += rotatedDelta.get(j);
			}
			answer = Math.min(answer, sum * i);
		}
		out.printLine(answer);
	}
}
