package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	int[] value;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println("Start");
		int queryCount = in.readInt();
		int[] addTo = IOUtils.readIntArray(in, queryCount);
//		Random random = new Random(239);
//		int[] addTo = new int[queryCount];
//		for (int i = 0; i < queryCount; i++) {
//			addTo[i] = random.nextInt(4 + 2 * i) + 1;
//		}
		MiscUtils.decreaseByOne(addTo);
		Graph graph = new BidirectionalGraph(4 + queryCount * 2, 3 + queryCount * 2);
		for (int i = 1; i < 4; i++)
			graph.addSimpleEdge(0, i);
		for (int i = 0; i < queryCount; i++) {
			graph.addSimpleEdge(addTo[i], 2 * i + 4);
			graph.addSimpleEdge(addTo[i], 2 * i + 5);
		}
//		LCA lca = new LCA(graph);
		int[] order = new int[2 * graph.vertexCount() - 1];
		int[] position = new int[graph.vertexCount()];
		int[] level = new int[graph.vertexCount()];
		int[] index = new int[graph.vertexCount()];
		for (int i = 0; i < index.length; i++)
			index[i] = graph.firstOutbound(i);
		int[] last = new int[graph.vertexCount()];
		int[] stack = new int[graph.vertexCount()];
		int size = 1;
		int j = 0;
		last[0] = -1;
		Arrays.fill(position, -1);
		while (size > 0) {
			int vertex = stack[--size];
			if (position[vertex] == -1)
				position[vertex] = j;
			order[j++] = vertex;
			if (last[vertex] != -1)
				level[vertex] = level[last[vertex]] + 1;
			while (index[vertex] != -1 && last[vertex] == graph.destination(index[vertex]))
				index[vertex] = graph.nextOutbound(index[vertex]);
			if (index[vertex] != -1) {
				stack[size++] = vertex;
				stack[size++] = graph.destination(index[vertex]);
				last[graph.destination(index[vertex])] = vertex;
				index[vertex] = graph.nextOutbound(index[vertex]);
			}
		}
		int[] spLevel = new int[order.length];
		for (int i = 0; i < order.length; i++)
			spLevel[i] = level[order[i]];
		value = new int[4 * spLevel.length];
		init(0, 0, spLevel.length - 1, spLevel);
//		IntervalTree lca = new ArrayBasedIntervalTree(spLevel) {
//			@Override
//			protected long joinValue(long left, long right) {
//				return Math.min(left, right);
//			}
//
//			@Override
//			protected long joinDelta(long was, long delta) {
//				return 0;
//			}
//
//			@Override
//			protected long accumulate(long value, long delta, int length) {
//				return value;
//			}
//
//			@Override
//			protected long neutralValue() {
//				return Integer.MAX_VALUE;
//			}
//
//			@Override
//			protected long neutralDelta() {
//				return 0;
//			}
//		};
		int from = 1;
		int to = 2;
		int length = 2;
		System.err.println("Preparation");
		for (int i = 0; i < queryCount; i++) {
			int vertex = 2 * i + 4;
			int candidate = getPathLength(vertex, from, level, position);
			if (candidate > length) {
				length = candidate;
				to = vertex;
			}
			candidate = getPathLength(vertex, to, level, position);
			if (candidate > length) {
				length = candidate;
				from = vertex;
			}
			vertex = 2 * i + 5;
			candidate = getPathLength(vertex, from, level, position);
			if (candidate > length) {
				length = candidate;
				to = vertex;
			}
			candidate = getPathLength(vertex, to, level, position);
			if (candidate > length) {
				length = candidate;
				from = vertex;
			}
			out.printLine(length);
		}
    }

	private void init(int root, int left, int right, int[] spLevel) {
		if (left == right) {
			value[root] = spLevel[left];
			return;
		}
		int middle = (left + right) >> 1;
		init(2 * root + 1, left, middle, spLevel);
		init(2 * root + 2, middle + 1, right, spLevel);
		value[root] = Math.min(value[2 * root + 1], value[2 * root + 2]);
	}

	private int getPathLength(int from, int to, int[] level, int[] position) {
		return (level[from] + level[to] - 2 * query(0, 0, 2 * position.length - 2, Math.min(position[from], position[to]), Math.max(position[from], position[to])));
	}

	private int query(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return Integer.MAX_VALUE;
		if (left >= from && right <= to)
			return value[root];
		int middle = (left + right) >> 1;
		return Math.min(query(2 * root + 1, left, middle, from, to), query(2 * root + 2, middle + 1, right, from, to));
	}
}
