package net.egork;

import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class Swords {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int delta = in.readInt();
		int[] age = IOUtils.readIntArray(in, count);
		Integer[] order = SequenceUtils.order(Array.wrap(age), new ReverseComparator<Integer>());
		int[] parent = new int[count];
		for (int ii = 1; ii < count; ii++) {
			int i = order[ii];
			int parentIndex = order[(ii - 1) / 2];
			if (age[parentIndex] - age[i] < delta) {
				out.println(-1);
				return;
			}
			parent[i] = parentIndex + 1;
		}
		IOUtils.printArray(parent, out);
	}
}
