package net.egork;

import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RecallingEarlyDaysGPWithTrees {
	private static final long MOD = 100711433;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int ratio = in.readInt();
		int reverseRatio = (int) IntegerUtils.reverse(ratio, MOD);
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		LCA lca = new LCA(graph);
		int updateCount = in.readInt();
		int queryCount = in.readInt();
		long[] deltaDirect = new long[count];
		long[] deltaReverse = new long[count];
		int[] parent = new int[count];
		go(0, -1, graph, parent);
		for (int i = 0; i < updateCount; i++) {
			int start = in.readInt() - 1;
			int end = in.readInt() - 1;
			long first = in.readLong();
			int cLCA = lca.getLCA(start, end);
			deltaDirect[start] += first;
			if (deltaDirect[start] >= MOD)
				deltaDirect[start] -= MOD;
			if (parent[cLCA] != -1) {
				deltaDirect[parent[cLCA]] -= IntegerUtils.power(ratio, lca.getPathLength(start, parent[cLCA]), MOD) * first % MOD;
				if (deltaDirect[parent[cLCA]] < 0)
					deltaDirect[parent[cLCA]] += MOD;
			}
			long last = first * IntegerUtils.power(ratio, lca.getPathLength(start, end), MOD) % MOD;
			deltaReverse[end] += last;
			if (deltaReverse[end] >= MOD)
				deltaReverse[end] -= MOD;
			deltaReverse[cLCA] -= IntegerUtils.power(reverseRatio, lca.getPathLength(end, cLCA), MOD) * last % MOD;
			if (deltaReverse[cLCA] < 0)
				deltaReverse[cLCA] += MOD;
		}
		long[] value = new long[count];
		go(0, -1, graph, deltaDirect, deltaReverse, ratio, reverseRatio, value);
		go(0, -1, graph, value);
		for (int i = 0; i < queryCount; i++) {
			int start = in.readInt() - 1;
			int end = in.readInt() - 1;
			int cLCA = lca.getLCA(start, end);
			long answer = value[start] + value[end] - value[cLCA];
			if (parent[cLCA] != -1)
				answer -= value[parent[cLCA]];
			answer %= MOD;
			if (answer < 0)
				answer += MOD;
			out.printLine(answer);
		}
    }

	private void go(int current, int last, Graph graph, long[] value) {
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last) {
				value[next] += value[current];
				if (value[next] >= MOD)
					value[next] -= MOD;
				go(next, current, graph, value);
			}
		}
	}

	private void go(int current, int last, Graph graph, long[] deltaDirect, long[] deltaReverse, int ratio, int reverseRatio, long[] value) {
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				go(next, current, graph, deltaDirect, deltaReverse, ratio, reverseRatio, value);
		}
		value[current] = deltaDirect[current] + deltaReverse[current];
		if (value[current] >= MOD)
			value[current] -= MOD;
		if (last != -1) {
			deltaDirect[last] += deltaDirect[current] * ratio % MOD;
			if (deltaDirect[last] >= MOD)
				deltaDirect[last] %= MOD;
			deltaReverse[last] += deltaReverse[current] * reverseRatio % MOD;
			if (deltaReverse[last] >= MOD)
				deltaReverse[last] %= MOD;
		}
	}

	private void go(int current, int last, Graph graph, int[] parent) {
		parent[current] = last;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				go(next, current, graph, parent);
		}
	}
}
