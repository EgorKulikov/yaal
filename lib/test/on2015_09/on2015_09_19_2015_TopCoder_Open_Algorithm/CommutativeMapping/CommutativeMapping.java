package on2015_09.on2015_09_19_2015_TopCoder_Open_Algorithm.CommutativeMapping;


import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class CommutativeMapping {
	static final long MOD = (long) (1e9 + 7);
	long[][] ways;
	Graph graph;
	boolean[] onCycle;
	int start;
	int start2;

	public int count(int[] f) {
		int n = f.length;
		int[] processed = new int[n];
		List<IntList> cycles = new ArrayList<>();
		onCycle = new boolean[n];
		graph = Graph.createGraph(n, f, ArrayUtils.createOrder(n));
		for (int i = 0; i < n; i++) {
			if (processed[i] != 0) {
				continue;
			}
			int current = i;
			while (processed[current] == 0) {
				processed[current] = 1;
				current = f[current];
			}
			if (processed[current] != 2) {
				int copy = current;
				IntList cycle = new IntArrayList();
				do {
					cycle.add(copy);
					onCycle[copy] = true;
					copy = f[copy];
				} while (copy != current);
				cycles.add(cycle);
			}
			for (int j = 0; j < n; j++) {
				if (processed[j] == 1) {
					processed[j] = 2;
				}
			}
		}
		ways = new long[n][n];
		ArrayUtils.fill(ways, -1);
		long answer = 1;
		for (IntList cycle : cycles) {
			long current = 0;
			for (int i = 0; i < n; i++) {
				current += calculate(cycle.get(0), i, true);
			}
			answer *= current % MOD;
			answer %= MOD;
		}
		return (int) answer;
	}

	private long calculate(int ver1, int ver2, boolean initial) {
		if (initial) {
			start = ver1;
			start2 = ver2;
		}
		if (ver1 == start && !initial) {
			return ver2 == start2 ? 1 : 0;
		}
		if (ways[ver1][ver2] != -1) {
			return ways[ver1][ver2];
		}
		long total = 1;
		for (int i = graph.firstOutbound(ver1); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
//			if (onCycle[next]) {
//				continue;
//			}
			long current = 0;
			for (int j = graph.firstOutbound(ver2); j != -1; j = graph.nextOutbound(j)) {
				current += calculate(next, graph.destination(j), false);
			}
			total *= current % MOD;
			total %= MOD;
		}
		return ways[ver1][ver2] = total;
	}
}
