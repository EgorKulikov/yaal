package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int setSize = in.readInt();
		int[] mass = new int[count];
		int[] cost = new int[count];
		IOUtils.readIntArrays(in, mass, cost);
		int[] quantity = new int[4001];
		for (int i : mass)
			quantity[i]++;
		int[][] costs = new int[4001][];
		for (int i = 0; i <= 4000; i++) {
			if (quantity[i] != 0)
				costs[i] = new int[quantity[i]];
		}
		int[] index = new int[4001];
		for (int i = 0; i < count; i++) {
			costs[mass[i]][index[mass[i]]++] = cost[i];
		}
		ArrayUtils.sort(quantity, IntComparator.REVERSE);
		int setCount = quantity[setSize - 1];
		int[] candidates = new int[4001];
		for (int i = 0; i <= 4000; i++) {
			if (costs[i] != null && costs[i].length >= setCount) {
				ArrayUtils.sort(costs[i], IntComparator.REVERSE);
				for (int j = 0; j < setCount; j++)
					candidates[i] += costs[i][j];
			}
		}
		ArrayUtils.sort(candidates, IntComparator.REVERSE);
		int totalCost = 0;
		for (int i = 0; i < setSize; i++)
			totalCost += candidates[i];
		out.printLine(setCount, totalCost);
	}
}
