package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] sizes = IOUtils.readIntArray(in, count);
		final int[][] time = new int[count][];
		for (int i = 0; i < count; i++)
			time[i] = IOUtils.readIntArray(in, sizes[i]);
		final int[][] weight = new int[count][];
		for (int i = 0; i < count; i++)
			weight[i] = IOUtils.readIntArray(in, sizes[i]);
		final long[] totalWeight = new long[count];
		final long[] totalTime = new long[count];
		for (int i = 0; i < count; i++) {
			totalTime[i] = ArrayUtils.sumArray(time[i]);
			totalWeight[i] = ArrayUtils.sumArray(weight[i]);
		}
		int[] order = new int[count];
		for (int i = 0; i < count; i++)
			order[i] = i;
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return IntegerUtils.longCompare(totalTime[first] * totalWeight[second], totalTime[second] * totalWeight[first]);
			}
		});
		int[] shifts = new int[count];
		for (int i = 1; i < count; i++)
			shifts[i] = shifts[i - 1] + sizes[i - 1];
		int[] answer = new int[shifts[count - 1] + sizes[count - 1]];
		int k = 0;
		long penalty = 0;
		long curTime = 0;
		for (final int i : order) {
			int[] localOrder = new int[sizes[i]];
			for (int j = 0; j < sizes[i]; j++)
				localOrder[j] = j;
			ArrayUtils.sort(localOrder, new IntComparator() {
				public int compare(int first, int second) {
					return IntegerUtils.longCompare(time[i][first] * weight[i][second], time[i][second] * weight[i][first]);
				}
			});
			for (int j : localOrder) {
				curTime += time[i][j];
				penalty += curTime * weight[i][j];
				answer[k++] = j + shifts[i] + 1;
			}
		}
		out.printLine(penalty);
		out.printLine(Array.wrap(answer).toArray());
	}
}
