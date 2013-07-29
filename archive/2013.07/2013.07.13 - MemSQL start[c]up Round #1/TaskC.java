package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntIterator;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {

	private int[] first;
	private int[] next;
	private int[] diamonds;
	private IntList[] to;
	private int[] max;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int changeCount = in.readInt();
		int count = in.readInt();
		first = new int[count];
		next = new int[changeCount];
		int[] inFirst = new int[count];
		int[] inNext = new int[100000];
		int[] edge = new int[100000];
		int edgeCount = 0;
		diamonds = new int[changeCount];
		int[] degree = new int[changeCount];
		int[] from = new int[changeCount];
		to = new IntList[changeCount];
		Arrays.fill(first, -1);
		Arrays.fill(inFirst, -1);
		for (int i = 0; i < changeCount; i++) {
			from[i] = in.readInt() - 1;
			next[i] = first[from[i]];
			first[from[i]] = i;
			int currentCount = in.readInt();
			to[i] = new IntArrayList(currentCount);
			for (int j = 0; j < currentCount; j++) {
				int type = in.readInt();
				if (type == -1)
					diamonds[i]++;
				else {
					type--;
					inNext[edgeCount] = inFirst[type];
					edge[edgeCount] = i;
					inFirst[type] = edgeCount++;
					to[i].add(type);
					degree[i]++;
				}
			}
		}
		final int[] min = new int[count];
		max = new int[count];
		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return min[first] - min[second];
			}
		}, count);
		boolean[] processed = new boolean[count];
		for (int i = 0; i < changeCount; i++) {
			if (degree[i] == 0) {
				if (processed[from[i]]) {
					min[from[i]] = Math.min(min[from[i]], diamonds[i]);
					heap.shiftUp(heap.getIndex(from[i]));
				} else {
					min[from[i]] = diamonds[i];
					heap.add(from[i]);
					processed[from[i]] = true;
				}
			}
		}
		while (!heap.isEmpty()) {
			int i = heap.poll();
			for (int jj = inFirst[i]; jj != -1; jj = inNext[jj]) {
				int j = edge[jj];
				degree[j]--;
				if (degree[j] == 0) {
					int total = diamonds[j];
					for (IntIterator iterator = to[j].iterator(); iterator.isValid(); iterator.advance()) {
						total = (int) Math.min(314000000, total + min[iterator.value()]);
					}
					if (processed[from[j]]) {
						if (min[from[j]] > total) {
							min[from[j]] = total;
							heap.shiftUp(heap.getIndex(from[j]));
						}
					} else {
						min[from[j]] = total;
						heap.add(from[j]);
						processed[from[j]] = true;
					}
				}
			}
		}
		for (int i = 0; i < count; i++) {
			if (!processed[i])
				min[i] = max[i] = -1;
		}
		for (int i = 0; i < count; i++) {
			if (max[i] != -2)
				dfs(i);
			out.printLine(min[i], max[i]);
		}
    }

	private int dfs(int i) {
		if (max[i] != 0)
			return max[i];
		max[i] = -2;
		int result = 0;
		for (int j = first[i]; j != -1; j = next[j]) {
			boolean valid = true;
			int total = diamonds[j];
			boolean infinity = false;
			for (IntIterator iterator = to[j].iterator(); iterator.isValid(); iterator.advance()) {
				int k = iterator.value();
				if (max[k] == -1) {
					valid = false;
					break;
				}
				dfs(k);
				if (max[k] == -2)
					infinity = true;
				else
					total = (int) Math.min(314000000, total + max[k]);
			}
			if (!valid)
				continue;
			if (infinity)
				return max[i];
			result = Math.max(result, total);
		}
		return max[i] = result;
	}
}
