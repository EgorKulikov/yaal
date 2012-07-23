package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskF {
	int[] first;
	int[] second;
	int[] cost;
	int maxSize;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int index = in.readInt() - 1;
		first = new int[edgeCount];
		second = new int[edgeCount];
		cost = new int[edgeCount];
		IOUtils.readIntArrays(in, first, second, cost);
		maxSize = index + 1;
		NavigableSet<State> set = new TreeSet<State>() {
			@Override
			public boolean add(State state) {
				super.add(state);
				if (size() > maxSize)
					pollLast();
				return true;
			}
		};
		Integer[] integerOrder = ListUtils.order(Array.wrap(cost));
		int[] order = new int[edgeCount];
		for (int i = 0; i < edgeCount; i++)
			order[i] = integerOrder[i];
		set.add(new State(0, 0, 0, 0, 0));
		for (int i = 0; i < index; i++) {
			State current = set.pollFirst();
			maxSize = index - i;
			for (int j : order) {
				if (set.size() == maxSize && current.cost + cost[j] >= set.last().cost)
					break;
				State next = current.add(j);
				if (next != null)
					set.add(next);
			}
		}
		out.printLine(set.first().cost);
	}

	class State implements Comparable<State> {
		final int menMask;
		final int womenMask;
		final long firstEdgeMask;
		final long secondEdgeMask;
		final int cost;

		State(int menMask, int womenMask, long firstEdgeMask, long secondEdgeMask, int cost) {
			this.menMask = menMask;
			this.womenMask = womenMask;
			this.firstEdgeMask = firstEdgeMask;
			this.secondEdgeMask = secondEdgeMask;
			this.cost = cost;
		}

		public int compareTo(State o) {
			if (cost != o.cost)
				return cost - o.cost;
			int value = IntegerUtils.longCompare(firstEdgeMask, o.firstEdgeMask);
			if (value != 0)
				return value;
			return IntegerUtils.longCompare(secondEdgeMask, o.secondEdgeMask);
		}

		public State add(int edge) {
			if ((menMask >> first[edge] & 1) != 0 || (womenMask >> second[edge] & 1) != 0)
				return null;
			return new State(menMask + (1 << first[edge]), womenMask + (1 << second[edge]),
				firstEdgeMask + (edge < 64 ? 1L << edge : 0), secondEdgeMask + (edge >= 64 ? 1L << (edge - 64) : 0),
				cost + TaskF.this.cost[edge]);
		}
	}
}
