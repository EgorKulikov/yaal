package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Coupons {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		final int count = in.readInt();
		int couponCount = in.readInt();
		long money = in.readLong();
		final int[] cost = new int[count];
		final int[] discountedCost = new int[count];
		IOUtils.readIntArrays(in, cost, discountedCost);
		Integer[] order = ListUtils.order(Array.wrap(discountedCost));
		NavigableSet<Integer> plain = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (cost[o1] != cost[o2])
					return cost[o1] - cost[o2];
				return o1 - o2;
			}
		});
		for (int i = 0; i < count; i++)
			plain.add(i);
		boolean[] taken = new boolean[count];
		Queue<Integer> couponUsed = new PriorityQueue<Integer>(couponCount + 1, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return (cost[o1] - discountedCost[o1]) - (cost[o2] - discountedCost[o2]);
			}
		});
		int answer = 0;
		for (int i : order) {
			if (taken[i]) {
				int toAdd = plain.pollFirst();
				taken[toAdd] = true;
				money -= cost[toAdd];
			} else {
				couponUsed.add(i);
				taken[i] = true;
				money -= discountedCost[i];
				if (couponUsed.size() > couponCount) {
					int toRemove = couponUsed.poll();
					money += discountedCost[toRemove];
					taken[toRemove] = false;
					plain.add(toRemove);
					int toAdd = plain.pollFirst();
					taken[toAdd] = true;
					money -= cost[toAdd];
				}
			}
			if (money < 0) {
				out.printLine(answer);
				return;
			}
			answer++;
		}
		out.printLine(count);
	}
}
