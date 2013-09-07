package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Intelligent {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		final long[] toStudy = new long[count];
		long[] day = new long[count];
		IOUtils.readLongArrays(in, day, toStudy);
		int tripCount = in.readInt();
		long[] start = new long[tripCount];
		long[] end = new long[tripCount];
		IOUtils.readLongArrays(in, start, end);
		int[] tripOrder = ArrayUtils.order(start);
		int[] examOrder = ArrayUtils.order(day);
		int j = 0;
		long decrease = 0;
		for (int i : examOrder) {
			while (j < tripCount && start[tripOrder[j]] < day[i]) {
				decrease += end[tripOrder[j]] - start[tripOrder[j]] + 1;
				j++;
			}
			day[i] -= decrease;
		}
		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return IntegerUtils.longCompare(toStudy[second], toStudy[first]);
			}
		}, count);
		long totalTime = 0;
		for (int i : examOrder) {
			if (totalTime + toStudy[i] + 1 <= day[i]) {
				heap.add(i);
				totalTime += toStudy[i] + 1;
			} else if (!heap.isEmpty() && toStudy[i] < toStudy[heap.peek()]) {
				totalTime -= toStudy[heap.poll()] + 1;
				totalTime += toStudy[i] + 1;
				heap.add(i);
			}
		}
		out.printLine(heap.getSize());
		int[] answer = new int[heap.getSize()];
		for (int i = 0; i < answer.length; i++)
			answer[i] = heap.poll() + 1;
		Arrays.sort(answer);
		out.printLine(answer);
    }
}
