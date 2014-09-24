package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		int[] weight = new int[count - 1];
		IOUtils.readIntArrays(in, from, to, weight);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		int[] firstQueue = new int[count];
		int[] secondQueue = new int[count];
		boolean[] inCurrent = new boolean[count];
		int[] processed = new int[count];
		int mark = 0;
		Arrays.fill(inCurrent, true);
		int[] order = ArrayUtils.order(weight);
		ArrayUtils.reverse(order);
		int[] allowed = IOUtils.readIntArray(in, count);
		int totalAllowed = 0;
		int remaining = count;
		DFSOrder dfsOrder = new DFSOrder((BidirectionalGraph)graph);
		IntervalTree tree = new LongIntervalTree(count) {
			@Override
			protected long joinValue(long left, long right) {
				return left + right;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				if (delta == neutralDelta()) {
					return was;
				}
				return delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				if (delta == neutralDelta()) {
					return value;
				}
				return delta * length;
			}

			@Override
			protected long neutralValue() {
				return 0;
			}

			@Override
			protected long neutralDelta() {
				return -1;
			}

			@Override
			protected long initValue(int index) {
				return 1;
			}
		};
		for (int i : order) {
			if (!inCurrent[from[i]]) {
				continue;
			}
			int toRemove;
			int parent;
			if (dfsOrder.position[from[i]] > dfsOrder.position[to[i]]) {
				int temp = from[i];
				from[i] = to[i];
				to[i] = temp;
			}
			int subSet = (int) tree.query(dfsOrder.position[to[i]], dfsOrder.end[to[i]]);
			if (subSet * 2 < remaining) {
				toRemove = to[i];
				parent = from[i];
				tree.update(dfsOrder.position[to[i]], dfsOrder.end[to[i]], 0);
				remaining -= subSet;
			} else {
				toRemove = from[i];
				parent = to[i];
				tree.update(0, dfsOrder.position[to[i]] - 1, 0);
				tree.update(dfsOrder.end[to[i]] + 1, count - 1, 0);
				remaining = subSet;
			}
			totalAllowed += dfs(toRemove, parent, graph, inCurrent, allowed);
			if (remaining <= totalAllowed) {
				out.printLine(weight[i]);
				return;
			}
		}
		out.printLine(0);
    }

	private int dfs(int current, int parent, Graph graph, boolean[] inCurrent, int[] allowed) {
		if (!inCurrent[current]) {
			return 0;
		}
		inCurrent[current] = false;
		int result = allowed[current];
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != parent) {
				result += dfs(next, current, graph, inCurrent, allowed);
			}
		}
		return result;
	}
}
