package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		IntList middle = new IntArrayList();
		int first = 0;
		int second = 0;
		for (int i = 0; i < count; i++) {
			int size = in.readInt();
			int[] stack = IOUtils.readIntArray(in, size);
			for (int j = 0; j < size / 2; j++)
				first += stack[j];
			for (int j = (size + 1) / 2; j < size; j++)
				second += stack[j];
			if (size % 2 == 1)
				middle.add(stack[size / 2]);
		}
		middle.inPlaceSort(IntComparator.REVERSE);
		for (int i = 0; i < middle.size(); i++) {
			if (i % 2 == 0)
				first += middle.get(i);
			else
				second += middle.get(i);
		}
		out.printLine(first, second);
    }
}
