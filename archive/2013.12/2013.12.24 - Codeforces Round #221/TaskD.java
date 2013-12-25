package net.egork;

import net.egork.collections.intcollection.IntHashMap;
import net.egork.collections.intcollection.IntIterator;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] colors = IOUtils.readIntArray(in, count);
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		int[] first = new int[count];
		int[] next = new int[queryCount];
		int[] min = new int[queryCount];
		Arrays.fill(first, -1);
		for (int i = 0; i < queryCount; i++) {
			int vertex = in.readInt() - 1;
			min[i] = in.readInt();
			next[i] = first[vertex];
			first[vertex] = i;
		}
		int[] answer = new int[queryCount];
		solve(0, -1, colors, graph, first, next, min, answer);
		for (int i : answer)
			out.printLine(i);
    }

	private Node solve(int vertex, int last, int[] colors, Graph graph, int[] first, int[] next, int[] min, int[] answer) {
		Node node = new Node(colors[vertex]);
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			if (graph.destination(i) == last)
				continue;
			node = node.unite(solve(graph.destination(i), vertex, colors, graph, first, next, min, answer));
		}
		int total = node.tree.query(Integer.MAX_VALUE);
		for (int i = first[vertex]; i != -1; i = next[i])
			answer[i] = total - node.tree.query(min[i] - 1);
		return node;
	}

	static class Node {
		IntHashMap colors = new IntHashMap();
		FenwickTree tree = new FenwickTree();

		Node(int color) {
			tree.modify(1, 1);
			colors.put(color, 1);
		}

		Node unite(Node other) {
			if (colors.size() < other.colors.size())
				return other.unite(this);
			for (IntIterator i = other.colors.iterator(); i.isValid(); i.advance()) {
				int color = i.value();
				int total = 0;
				if (colors.contains(color))
					tree.modify(total = colors.get(color), -1);
				total += other.colors.get(color);
				tree.modify(total, 1);
				colors.put(color, total);
			}
			return this;
		}
	}

	static class FenwickTree {
		int[] values = new int[3];

		final void modify(int at, int delta) {
			ensureCapacity(at);
			modify(values, at, delta);
		}

		private void ensureCapacity(int at) {
			if (at >= values.length) {
				int newLength = 2 * values.length - 1;
				while (at >= newLength)
					newLength = 2 * newLength - 1;
				int[] newValues = new int[newLength];
				int sum = 0;
				for (int i = 1; i < values.length; i++) {
					int newSum = query(i);
					modify(newValues, i, newSum - sum);
					sum = newSum;
				}
				values = newValues;
			}
		}

		private void modify(int[] values, int at, int delta) {
			while (at < values.length) {
				values[at] += delta;
				at += at - (at & (at - 1));
			}
		}

		final int query(int upTo) {
			upTo = Math.min(upTo, values.length - 1);
			int result = 0;
			while (upTo > 0) {
				result += values[upTo];
				upTo -= upTo - (upTo & (upTo - 1));
			}
			return result;
		}
	}
}
