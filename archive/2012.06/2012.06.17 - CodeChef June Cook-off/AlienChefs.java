package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class AlienChefs {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long time = System.currentTimeMillis();
		int count = in.readInt();
		int[] start = new int[count];
		int[] end = new int[count];
		IOUtils.readIntArrays(in, start, end);
		int[] interestingTimes = new int[2 * count];
		System.arraycopy(start, 0, interestingTimes, 0, count);
		System.arraycopy(end, 0, interestingTimes, count, count);
		int[] order = new int[count];
		int[] copy = new int[count];
		for (int i = 0; i < count; i++)
			copy[i] = order[i] = i;
		sort(order, copy, start, 0, count);
		int[] realStart = new int[count];
		int[] realEnd = new int[count];
		for (int i = 0; i < count; i++) {
			realStart[i] = start[order[i]];
			realEnd[i] = end[order[i]];
		}
		start = realStart;
		end = realEnd;
		System.err.println("Order " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		IntervalTree tree = new IntervalTree(start, end);
		int queryCount = in.readInt();
		System.err.println("Init " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		for (int i = 0; i < queryCount; i++) {
			int alienCount = in.readInt();
			int[] visits = IOUtils.readIntArray(in, alienCount);
			Arrays.sort(visits);
			int result = count;
			for (int j = 0; j <= alienCount; j++) {
				int begin = j == 0 ? 0 : visits[j - 1] + 1;
				int finish = j == alienCount ? (int)2e9 : visits[j];
				result -= tree.query(begin, finish);
			}
			out.printLine(result);
		}
		System.err.println("Queries " + (System.currentTimeMillis() - time));
	}

	private void sort(int[] order, int[] copy, int[] array, int from, int to) {
		if (to - from == 1)
			return;
		int middle = (from + to) >> 1;
		sort(copy, order, array, from, middle);
		sort(copy, order, array, middle, to);
		int index1 = from;
		int index2 = middle;
		int index = from;
		while (index1 < middle && index2 < to) {
			if (array[copy[index1]] <= array[copy[index2]])
				order[index++] = copy[index1++];
			else
				order[index++] = copy[index2++];
		}
		if (index1 != middle)
			System.arraycopy(copy, index1, order, index, middle - index1);
		if (index2 != to)
			System.arraycopy(copy, index2, order, index, to - index2);
	}
}

class IntervalTree {
	protected int size;
	protected int[][] nodes;
	protected int[] start;
	protected int[] end;

	protected IntervalTree(int[] starts, int[] ends) {
		this.size = ends.length;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		//noinspection unchecked
		nodes = new int[nodeCount][];
		start = new int[nodeCount];
		end = new int[nodeCount];
		init(0, 0, size - 1, ends, starts);
	}

	private void init(int root, int left, int right, int[] ends, int[] starts) {
		start[root] = starts[left];
		end[root] = starts[right];
		if (left == right)
			nodes[root] = new int[]{ends[left]};
		else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, ends, starts);
			init(2 * root + 2, middle + 1, right, ends, starts);
			nodes[root] = new int[right - left + 1];
			System.arraycopy(nodes[2 * root + 1], 0, nodes[root], 0, nodes[2 * root + 1].length);
			System.arraycopy(nodes[2 * root + 2], 0, nodes[root], nodes[2 * root + 1].length, nodes[2 * root + 2].length);
			Arrays.sort(nodes[root]);
		}
	}

	public int query(int from, int to) {
		return query(0, 0, size - 1, from, to);
	}

	private int query(int root, int left, int right, int from, int to) {
		if (start[root] >= to || end[root] < from)
			return 0;
		if (start[root] >= from && end[root] <= to)
			return binarySearch(nodes[root], to);
		int middle = (left + right) >> 1;
		return query(2 * root + 1, left, middle, from, to) + query(2 * root + 2, middle + 1, right, from, to);
	}

	private int binarySearch(int[] array, int value) {
		int left = 0;
		int right = array.length;
		while (left < right) {
			int middle = (left + right) >> 1;
			if (array[middle] < value)
				left = middle + 1;
			else
				right = middle;
		}
		return left;
	}
}
