package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long target = in.readLong();
		final int[] profit = new int[count];
		final int[] cost = new int[count];
		IOUtils.readIntArrays(in, profit, cost);
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (cost[first] != cost[second])
					return cost[first] - cost[second];
				return profit[second] - profit[first];
			}
		});
		boolean[] toSkip = new boolean[count];
		int maxProfitSoFar = 0;
		for (int i : order) {
			if (profit[i] <= maxProfitSoFar)
				toSkip[i] = true;
			else
				maxProfitSoFar = profit[i];
		}
		long[] a = new long[count];
		long[] b = new long[count];
		long[] time = new long[count];
		int size = 0;
		for (int i : order) {
			if (toSkip[i])
				continue;
			if (size == 0) {
				a[0] = profit[i];
				size = 1;
				continue;
			}
			int left = 0;
			int right = size - 1;
			while (left < right) {
				int middle = (left + right) >> 1;
				if (a[middle] * (time[middle + 1] - 1) + b[middle] >= cost[i])
					right = middle;
				else
					left = middle + 1;
			}
			long at = (cost[i] - b[left] + a[left] - 1) / a[left];
			a[size] = profit[i];
			b[size] = a[left] * at + b[left] - a[size] * at - cost[i];
			size++;
			while (true) {
				time[size - 1] = (b[size - 2] - b[size - 1] + (a[size - 1] - a[size - 2] - 1)) / (a[size - 1] - a[size - 2]);
				if (b[size - 2] <= b[size - 1] || time[size - 1] <= time[size - 2]) {
					a[size - 2] = a[size - 1];
					b[size - 2] = b[size - 1];
					size--;
					continue;
				}
				break;
//				if (a[size - 2] * time[size - 2] + b[size - 2] <= a[size - 1] * time[size - 2] + b[size - 1]) {
//					size--;
//				} else
//					break;
			}
		}
		long answer = Long.MAX_VALUE;
		for (int i = 0; i < size; i++)
			answer = Math.min(answer, (target - b[i] + a[i] - 1) / a[i]);
		out.printLine(answer);
	}
}
