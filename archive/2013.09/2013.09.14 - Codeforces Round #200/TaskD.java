package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph undirected = BidirectionalGraph.createGraph(count, from, to);
		int[] queue = new int[count];
		int size = 1;
		boolean[] processed = new boolean[count];
		Graph directed = new Graph(count, count - 1);
		for (int i = 0; i < count; i++) {
			int current = queue[i];
			processed[current] = true;
			for (int j = undirected.firstOutbound(current); j != -1; j = undirected.nextOutbound(j)) {
				int next = undirected.destination(j);
				if (!processed[next]) {
					directed.addSimpleEdge(current, next);
					queue[size++] = next;
				}
			}
		}
		DFSOrder order = new DFSOrder(directed);
		MaxTree adds = new MaxTree(count);
		MaxTree removes = new MaxTree(count);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			int vertex = in.readInt() - 1;
			if (type == 1)
				adds.update(order.position[vertex], order.end[vertex], i);
			else if (type == 2)
				removes.update(order.position[vertex], order.position[vertex], i);
			else
				out.printLine(adds.query(order.position[vertex], order.position[vertex]) > removes.query(order.position[vertex], order.end[vertex]) ? '1' : '0');
		}
	}

	static class MaxTree extends LongIntervalTree {
		protected MaxTree(int size) {
			super(size);
		}

		@Override
		protected long joinValue(long left, long right) {
			return Math.max(left, right);
		}

		@Override
		protected long joinDelta(long was, long delta) {
			return Math.max(was, delta);
		}

		@Override
		protected long accumulate(long value, long delta, int length) {
			return Math.max(value, delta);
		}

		@Override
		protected long neutralValue() {
			return -1;
		}

		@Override
		protected long neutralDelta() {
			return -1;
		}
	}
}
