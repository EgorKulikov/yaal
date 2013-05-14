package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Problem15 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] id = new int[count];
		final int[] score = new int[count];
		IOUtils.readIntArrays(in, id, score);
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (score[first] != score[second])
					return score[second] - score[first];
				return first - second;
			}
		});
		for (int i : order)
			out.printLine(id[i], score[i]);
    }
}
