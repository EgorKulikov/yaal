package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntIterator;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Gangs {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int gangCount = in.readInt();
		int[] perGang = IOUtils.readIntArray(in, gangCount);
		int remainingFirst = first(perGang);
		if (remainingFirst == 0) {
			out.printLine("NO");
			return;
		}
		IntList[] pairedWith = new IntList[gangCount];
		for (int i = 0; i < pairedWith.length; i++)
			pairedWith[i] = new IntArrayList();
		for (int i = 0; i < gangCount; i++) {
			for (int j = i + 1; j < gangCount; j++) {
				while (perGang[i] > 0 && perGang[j] > 0) {
					perGang[i]--;
					perGang[j]--;
					if (first(perGang) == remainingFirst)
						pairedWith[i].add(j);
					else {
						perGang[i]++;
						perGang[j]++;
						break;
					}
				}
			}
		}
		out.printLine("YES");
		out.printLine(remainingFirst);
		boolean onlyFirst = true;
		for (int i = 1; i < gangCount; i++) {
			if (pairedWith[i].size() > 0) {
				onlyFirst = false;
				break;
			}
		}
		if (onlyFirst) {
			for (int i = 0; i < remainingFirst; i++) {
				out.printLine(1);
			}
		}
		for (int i = 0; i < gangCount; i++) {
			for (int j = 0; j < pairedWith[i].size(); j++)
				out.printLine(i + 1);
			for (IntIterator iterator = pairedWith[i].iterator(); iterator.isValid(); iterator.advance())
				out.printLine(iterator.value() + 1);
		}
		if (!onlyFirst) {
			for (int i = 0; i < remainingFirst; i++) {
				out.printLine(1);
			}
		}
	}

	private int first(int[] originalCount) {
		final int[] count = originalCount.clone();
		Heap heap = new Heap(count.length - 1, new IntComparator() {
			public int compare(int first, int second) {
				return count[second] - count[first];
			}
		}, count.length);
		for (int i = 1; i < count.length; i++) {
			if (count[i] != 0)
				heap.add(i);
		}
		while (heap.getSize() > 1) {
			int first = heap.poll();
			int second = heap.poll();
			count[first]--;
			count[second]--;
			if (count[first] != 0)
				heap.add(first);
			if (count[second] != 0)
				heap.add(second);
		}
		if (heap.getSize() == 0)
			return count[0];
		return Math.max(0, count[0] - count[heap.peek()]);
	}
}
