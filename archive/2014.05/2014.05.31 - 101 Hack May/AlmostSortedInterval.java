package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class AlmostSortedInterval {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		final int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(array);
		int[] stack = new int[count];
		int size = 0;
		int[] nextLess = new int[count];
		for (int i = count - 1; i >= 0; i--) {
			while (size > 0 && array[stack[size - 1]] > array[i])
				size--;
			if (size > 0)
				nextLess[i] = stack[size - 1];
			else
				nextLess[i] = count;
			stack[size++] = i;
		}
		size = 0;
		int[] prevMore = new int[count];
		for (int i = 0; i < count; i++) {
			while (size > 0 && array[stack[size - 1]] < array[i])
				size--;
			if (size > 0)
				prevMore[i] = stack[size - 1];
			else
				prevMore[i] = -1;
			stack[size++] = i;
		}
		IntervalTree tree = new SumIntervalTree(count);
		int[] first = new int[count + 1];
		int[] next = new int[count];
		Arrays.fill(first, -1);
		for (int i = 0; i < count; i++) {
			next[i] = first[nextLess[i]];
			first[nextLess[i]] = i;
		}
		long answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = first[i]; j != -1; j = next[j])
				tree.update(j, j, -1);
			tree.update(i, i, 1);
			answer += tree.query(prevMore[i] + 1, i);
		}
		out.printLine(answer);
    }
}
