package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int bagCount = in.readInt();
		int[] mass = new int[count];
		final int[] value = new int[count];
		IOUtils.readIntArrays(in, mass, value);
		int[] capacity = IOUtils.readIntArray(in, bagCount);
		ArrayUtils.orderBy(mass, value);
		ArrayUtils.sort(capacity, IntComparator.DEFAULT);
		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return value[second] - value[first];
			}
		}, count);
		int j = 0;
		long answer = 0;
		for (int i : capacity) {
			while (j < count && mass[j] <= i)
				heap.add(j++);
			if (!heap.isEmpty())
				answer += value[heap.poll()];
		}
		out.printLine(answer);
    }
}
