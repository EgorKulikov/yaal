package net.egork.collections.intervaltree;

import java.util.Arrays;

/**
 * @author egor@egork.net
 */
public class LCA {
	private final long[] order;
	private final int[] position;
	private final int[][] graph;
	private final LongIntervalTree lcaTree;
	private final int[] level;

	public LCA(int[][] graph) {
		this.graph = graph;
		order = new long[2 * graph.length - 1];
		position = new int[graph.length];
		level = new int[graph.length];
		int[] index = new int[graph.length];
		int[] last = new int[graph.length];
		int[] stack = new int[graph.length];
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
			while (index[vertex] < graph[vertex].length && last[vertex] == graph[vertex][index[vertex]])
				index[vertex]++;
			if (index[vertex] < graph[vertex].length) {
				stack[size++] = vertex;
				stack[size++] = graph[vertex][index[vertex]];
				last[graph[vertex][index[vertex]++]] = vertex;
			}
		}
		lcaTree = new ArrayBasedIntervalTree(order) {
			@Override
			protected long joinValue(long left, long right) {
				if (left == -1)
					return right;
				if (right == -1)
					return left;
				if (level[((int) left)] < level[((int) right)])
					return left;
				return right;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return was;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return value;
			}

			@Override
			protected long neutralValue() {
				return -1;
			}

			@Override
			protected long neutralDelta() {
				return 0;
			}
		};
		lcaTree.init();
	}

	public int getPosition(int vertex) {
		return position[vertex];
	}

	public int getLCA(int first, int second) {
		return (int) lcaTree.query(Math.min(position[first], position[second]), Math.max(position[first], position[second]));
	}

	public int getLevel(int vertex) {
		return level[vertex];
	}

	public int getPathLength(int first, int second) {
		return level[first] + level[second] - 2 * level[getLCA(first, second)];
	}

	private int calculate(int vertex, int last, int currentPosition) {
		if (last != -1)
			level[vertex] = level[last] + 1;
		position[vertex] = currentPosition;
		order[currentPosition++] = vertex;
		for (int i : graph[vertex]) {
			if (i != last) {
				currentPosition = calculate(i, vertex, currentPosition);
				order[currentPosition++] = vertex;
			}
		}
		return currentPosition;
	}
}
