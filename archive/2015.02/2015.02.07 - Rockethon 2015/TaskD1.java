package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD1 {
	int at;
	int[] answer;
	Graph graph;
	IntervalTree tree;
	int[] qStart;
	int[] qEnd;
	int[] qStartAt;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		qStart = new int[count];
		qEnd = new int[count];
		qStartAt = new int[count];
		int constraintCount = in.readInt();
		int[] parent = new int[constraintCount];
		int[] child = new int[constraintCount];
		graph = new Graph(count, constraintCount);
		boolean[] toRight = new boolean[constraintCount];
		for (int i = 0; i < constraintCount; i++) {
			parent[i] = in.readInt() - 1;
			child[i] = in.readInt() - 1;
			toRight[i] = in.readString().charAt(0) == 'R';
			if (parent[i] >= child[i]) {
				out.printLine("IMPOSSIBLE");
				return;
			}
			graph.addWeightedEdge(parent[i], child[i], toRight[i] ? 1 : -1);
		}
		tree = new LongIntervalTree(count) {
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

			@Override
			protected long initValue(int index) {
				return index;
			}
		};
		int[] order = ArrayUtils.order(parent);
		ArrayUtils.reverse(order);
		for (int i : order) {
			tree.update(parent[i], parent[i], tree.query(parent[i], child[i]));
		}
		answer = new int[count];
		qEnd[0] = count - 1;
		at = 1;
		for (int i = 0; i < count; i++) {
			if (!solve(qStart[i], qEnd[i], qStartAt[i])) {
				out.printLine("IMPOSSIBLE");
				return;
			}
		}
		out.printLine(answer);
    }

	boolean solve(int start, int end, int startAt) {
		if (start == end) {
			answer[startAt] = start + 1;
			return true;
		}
		int rightStart = start + 1;
		for (int i = graph.firstOutbound(start); i != -1; i = graph.nextOutbound(i)) {
			if (graph.weight(i) == -1) {
				rightStart = (int) Math.max(rightStart, tree.query(start + 1, graph.destination(i)) + 1);
			}
		}
		for (int i = graph.firstOutbound(start); i != -1; i = graph.nextOutbound(i)) {
			if (graph.weight(i) == 1 && graph.destination(i) < rightStart) {
				return false;
			}
		}
		if (rightStart - 1 >= start + 1) {
			qStart[at] = start + 1;
			qEnd[at] = rightStart - 1;
			qStartAt[at++] = startAt;
		}
		if (end >= rightStart) {
			qStart[at] = rightStart;
			qEnd[at] = end;
			qStartAt[at++] = rightStart - start + startAt;
		}
		answer[rightStart - start - 1 + startAt] = start + 1;
		return true;
	}
}
