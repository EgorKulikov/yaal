package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intervaltree.ArrayBasedIntervalTree;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class MaximumNumberGCDCondition {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		IntList[] indLists = new IntList[100001];
		for (int i = 0; i < count; i++) {
			int copy = numbers[i];
			for (int j = 2; j * j <= copy; j++) {
				if (copy % j == 0) {
					if (indLists[j] == null)
						indLists[j] = new IntArrayList();
					indLists[j].add(i);
					do {
						copy /= j;
					} while (copy % j == 0);
				}
			}
			if (copy != 1) {
				if (indLists[copy] == null)
					indLists[copy] = new IntArrayList();
				indLists[copy].add(i);
			}
		}
		int[][] indices = new int[100000][];
		IntervalTree[] trees = new IntervalTree[100000];
		for (int i = 2; i < 100000; i++) {
			if (indLists[i] != null) {
				indices[i] = indLists[i].toArray();
				long[] values = new long[indices[i].length];
				for (int j = 0; j < indices[i].length; j++)
					values[j] = numbers[indices[i][j]];
				trees[i] = new ReadOnlyIntervalTree(values) {
					@Override
					protected long joinValue(long left, long right) {
						return Math.max(left, right);
					}

					@Override
					protected long neutralValue() {
						return -1;
					}
				};
			}
		}
		Arrays.fill(indLists, null);
		for (int i = 0; i < count; i++) {
			if (indLists[numbers[i]] == null)
				indLists[numbers[i]] = new IntArrayList();
			indLists[numbers[i]].add(i);
		}
		int[][] at = new int[100001][];
		for (int i = 1; i <= 100000; i++) {
			if (indLists[i] != null) {
				at[i] = indLists[i].toArray();
			}
		}
		for (int i = 0; i < queryCount; i++) {
			int g = in.readInt();
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int max = -1;
			for (int j = 2; j * j <= g; j++) {
				if (g % j == 0) {
					if (indices[j] != null)
						max = Math.max(max, check(indices[j], from, to, trees[j]));
					do {
						g /= j;
					} while (g % j == 0);
				}
			}
			if (g != 1 && indices[g] != null)
				max = Math.max(max, check(indices[g], from, to, trees[g]));
			if (max == -1) {
				out.printLine(-1, -1);
				continue;
			}
			from = Arrays.binarySearch(at[max], from);
			if (from < 0)
				from = -from - 1;
			to = Arrays.binarySearch(at[max], to);
			if (to < 0)
				to = -to - 2;
			out.printLine(max, to - from + 1);
		}
    }

	private int check(int[] indices, int from, int to, IntervalTree tree) {
		from = Arrays.binarySearch(indices, from);
		if (from < 0)
			from = -from - 1;
		to = Arrays.binarySearch(indices, to);
		if (to < 0)
			to = -to - 2;
		return (int) tree.query(from, to);
	}
}
