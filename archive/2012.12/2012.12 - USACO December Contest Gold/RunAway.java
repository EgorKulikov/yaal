package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.set.TreapSet;
import net.egork.graph.GraphUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;

public class RunAway {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long maxDistance = in.readLong();
		int[] parent = new int[count];
		final long[] length = new long[count + 1];
		for (int i = 1; i < count; i++) {
			parent[i] = in.readInt() - 1;
			length[i] = in.readLong();
		}
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		for (int i = 0; i < count - 1; i++)
			to[i] = i + 1;
		System.arraycopy(parent, 1, from, 0, count - 1);
		int[][] graph = GraphUtils.buildSimpleOrientedGraph(count, from, to);
		int[] queue = new int[count];
		int size = 1;
		for (int i = 0; i < size; i++) {
			for (int j : graph[queue[i]]) {
				length[j] += length[parent[j]];
				queue[size++] = j;
			}
		}
		NavigableSet<Integer>[] sets = new NavigableSet[count];
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = IntegerUtils.longCompare(length[o1], length[o2]);
				if (value != 0)
					return value;
				return o1 - o2;
			}
		};
		for (int i = 0; i < count; i++) {
			sets[i] = new TreapSet<Integer>(comparator);
			sets[i].add(i);
		}
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return -IntegerUtils.longCompare(length[first], length[second]);
			}
		});
		int[] answer = new int[count];
		for (int i : order) {
			length[count] = length[i] + maxDistance;
			answer[i] = sets[i].headSet(count).size();
			if (i != 0) {
				if (sets[parent[i]].size() >= sets[i].size())
					sets[parent[i]].addAll(sets[i]);
				else {
					sets[i].addAll(sets[parent[i]]);
					sets[parent[i]] = sets[i];
				}
			}
		}
		for (int i : answer)
			out.printLine(i);
	}
}
