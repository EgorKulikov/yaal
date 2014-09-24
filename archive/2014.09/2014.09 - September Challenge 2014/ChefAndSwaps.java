package net.egork;

import net.egork.collections.FenwickTree;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntHashMap;
import net.egork.collections.intcollection.IntSplaySet;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.collections.map.CPPMap;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.Factory;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;

public class ChefAndSwaps {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		final int[] array = IOUtils.readIntArray(in, size);
		ArrayUtils.compress(array);
		int[] first = new int[count];
		int[] second = new int[count];
		IOUtils.readIntArrays(in, first, second);
		MiscUtils.decreaseByOne(first, second);
		FenwickTree tree = new FenwickTree(size);
		long[] answer = new long[count];
		int[] at = new int[4 * count];
		int[] by = new int[4 * count];
		int[] sign = new int[4 * count];
		int[] id = new int[4 * count];
		for (int i = 0; i < count; i++) {
			id[4 * i] = i;
			at[4 * i] = first[i];
			by[4 * i] = first[i];
			sign[4 * i] = -1;
			id[4 * i + 1] = i;
			at[4 * i + 1] = second[i];
			by[4 * i + 1] = first[i];
			sign[4 * i + 1] = 1;
			id[4 * i + 2] = i;
			at[4 * i + 2] = first[i];
			by[4 * i + 2] = second[i];
			sign[4 * i + 2] = 1;
			id[4 * i + 3] = i;
			at[4 * i + 3] = second[i];
			by[4 * i + 3] = second[i];
			sign[4 * i + 3] = -1;
			if (array[first[i]] != array[second[i]]) {
				answer[i]++;
			}
		}
		ArrayUtils.orderBy(at, by, sign, id);
		int j = 0;
		long base = 0;
		for (int i = 0; i < size; i++) {
			while (j < 4 * count && at[j] == i) {
				answer[id[j]] += sign[j] * tree.get(array[by[j]] + 1, size - 1);
				j++;
			}
			base += tree.get(array[i] + 1, size);
			tree.add(array[i], 1);
		}
		tree = new FenwickTree(size);
		j = 4 * count - 1;
		for (int i = size - 1; i >= 0; i--) {
			while (j >= 0 && at[j] == i) {
				answer[id[j]] += sign[j] * tree.get(0, array[by[j]] - 1);
				j--;
			}
			tree.add(array[i], 1);
		}
		for (long l : answer) {
			out.printLine(l + base);
		}
	}
}
