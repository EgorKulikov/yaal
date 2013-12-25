package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int mayRemove = in.readInt();
		int[] colors = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(colors);
		final int[] qty = new int[count];
		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return qty[second] - qty[first];
			}
		}, count);
		int start = 0;
		int total = 0;
		int answer = 0;
		for (int i : colors) {
			qty[i]++;
			if (heap.getIndex(i) == -1)
				heap.add(i);
			else
				heap.shiftUp(heap.getIndex(i));
			total++;
			while (total - qty[heap.peek()] > mayRemove) {
				qty[colors[start]]--;
				heap.shiftDown(heap.getIndex(colors[start++]));
				total--;
			}
			answer = Math.max(answer, qty[heap.peek()]);
		}
		out.printLine(answer);
    }
}
