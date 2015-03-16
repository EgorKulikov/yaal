package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		int can = in.readInt();
		long[] values = IOUtils.readLongArray(in, size);
		int[] type = new int[count];
		int[] id = new int[count];
		int[] by = new int[count];
		IOUtils.readIntArrays(in, type, id, by);
		MiscUtils.decreaseByOne(id);
		int[] set = new int[size];
		Arrays.fill(set, -1);
		for (int i = 0; i < count; i++) {
			if (type[i] == 1) {
				if (by[i] > values[id[i]] && (set[id[i]] == -1 || by[set[id[i]]] < by[i])) {
					set[id[i]] = i;
				}
			}
		}
		for (int i = 0; i < count; i++) {
			if (type[i] == 1 && set[id[i]] == i) {
				by[i] -= values[id[i]];
			}
		}
		IntList[] lists = new IntList[size];
		for (int i = 0; i < count; i++) {
			if (type[i] == 2 || type[i] == 1 && set[id[i]] == i) {
				if (lists[id[i]] == null) {
					lists[id[i]] = new IntArrayList();
				}
				lists[id[i]].add(i);
			}
		}
		int[][] addOrder = new int[size][];
		IntComparator comparator = (first, second) -> by[second] - by[first];
		for (int i = 0; i < size; i++) {
			if (lists[i] == null) {
				addOrder[i] = new int[0];
			} else {
				lists[i].inPlaceSort(comparator);
				addOrder[i] = lists[i].toArray();
			}
		}
		Rational[] multiply = new Rational[count];
		Heap order = new Heap((first, second) -> -multiply[first].compareTo(multiply[second]), count);
		int[] pointer = new int[size];
		for (int i = 0; i < size; i++) {
			if (addOrder[i].length != 0) {
				int at = addOrder[i][0];
				multiply[at] = new Rational(values[i] + by[at], values[i]);
				order.add(at);
			}
		}
		for (int i = 0; i < count; i++) {
			if (type[i] == 3) {
				multiply[i] = new Rational(by[i], 1);
				order.add(i);
			}
		}
		IntList answer = new IntArrayList();
		for (int i = 0; i < can && !order.isEmpty(); i++) {
			int current = order.poll();
			answer.add(current + 1);
			if (type[current] != 3) {
				int at = id[current];
				values[at] += by[current];
				if (++pointer[at] < addOrder[at].length) {
					int next = addOrder[at][pointer[at]];
					multiply[next] = new Rational(values[at] + by[next], values[at]);
					order.add(next);
				}
			}
		}
		answer.inPlaceSort((first, second) -> type[first - 1] - type[second - 1]);
		out.printLine(answer.size());
		out.printLine(answer);
	}
}
