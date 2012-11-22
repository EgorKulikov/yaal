package net.egork;

import net.egork.collections.set.EHashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CirclesGame {
	int[] parent;

	public String whoCanWin(int[] x, int[] y, int[] r) {
		int count = x.length;
		parent = new int[count];
		for (int i = 0; i < count; i++) {
			int minRadius = Integer.MAX_VALUE;
			parent[i] = -1;
			for (int j = 0; j < count; j++) {
				int dx = x[i] - x[j];
				int dy = y[i] - y[j];
				int dr = r[i] - r[j];
				if (r[j] > r[i] && r[j] < minRadius && dx * dx + dy * dy < dr * dr) {
					minRadius = r[j];
					parent[i] = j;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < count; i++) {
			if (parent[i] == -1)
				result ^= resolve(go(i));
		}
		return result == 0 ? "Bob" : "Alice";
	}

	private int resolve(Set<Integer> set) {
		for (int i = 0; ; i++) {
			if (!set.contains(i))
				return i;
		}
	}

	private Set<Integer> go(int vertex) {
		int total = 0;
		List<Set<Integer>> sets = new ArrayList<Set<Integer>>();
		List<Integer> resolved = new ArrayList<Integer>();
		for (int i = 0; i < parent.length; i++) {
			if (parent[i] == vertex) {
				Set<Integer> callResult = go(i);
				sets.add(callResult);
				int resolveValue = resolve(callResult);
				total ^= resolveValue;
				resolved.add(resolveValue);
			}
		}
		Set<Integer> result = new EHashSet<Integer>();
		result.add(total);
		for (int i = 0; i < sets.size(); i++) {
			int current = total ^ resolved.get(i);
			for (int j : sets.get(i))
				result.add(j ^ current);
		}
		return result;
	}
}
