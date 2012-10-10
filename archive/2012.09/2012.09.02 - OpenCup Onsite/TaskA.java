package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] cost = new int[count];
		final int[] added = new int[count];
		int[] removed = new int[count];
		for (int i = 0; i < count; i++) {
			cost[i] = in.readInt();
			added[i] = in.readInt();
			removed[i] = in.readInt();
		}
		Integer[] order = new Integer[count];
		for (int i = 0; i < count; i++)
			order[i] = i;
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return added[o1] - added[o2];
			}
		});
		int queryCount = in.readInt();
		int[] start = new int[queryCount];
		int[] value = new int[queryCount];
		int[] end = new int[queryCount];
		for (int i = 0; i < queryCount; i++) {
			start[i] = in.readInt();
			value[i] = in.readInt();
			end[i] = start[i] + in.readInt();
		}
		int[] sortedAdded = added.clone();
		Arrays.sort(sortedAdded);
		for (int i = 0; i < count; i++)
			sortedAdded[i] *= 2;
		int[][] after = new int[count][];
		int[] qAfter = new int[count];
		int[] index = new int[queryCount];
		for (int i = 0; i < queryCount; i++) {
			index[i] = Arrays.binarySearch(sortedAdded, 2 * start[i] + 1);
			index[i] = -index[i] - 2;
			if (index[i] >= 0)
				qAfter[index[i]]++;
		}
		for (int i = 0; i < count; i++)
			after[i] = new int[qAfter[i]];
		for (int i = 0; i < queryCount; i++) {
			if (index[i] != -1)
				after[index[i]][--qAfter[index[i]]] = i;
		}
		boolean[] answer = new boolean[queryCount];
		int[] minEnd = new int[100001];
		minEnd[0] = Integer.MAX_VALUE;
		int j = 0;
		for (int i : order) {
			for (int k = 100000; k >= cost[i]; k--)
				minEnd[k] = Math.max(minEnd[k], Math.min(minEnd[k - cost[i]], removed[i]));
			for (int k : after[j]) {
				if (minEnd[value[k]] > end[k])
					answer[k] = true;
			}
			j++;
		}
		for (boolean b : answer) {
			if (b)
				out.printLine("TAK");
			else
				out.printLine("NIE");
		}
	}
}
