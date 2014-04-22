package net.egork.collections.intervaltree;

import net.egork.graph.Graph;

import java.util.Arrays;

/**
 * @author egor@egork.net
 */
public class LCA {
	private final long[] order;
	private final int[] position;
	private final Graph graph;
	private final IntervalTree lcaTree;
	private final int[] level;

	public LCA(Graph graph) {
		this(graph, 0);
	}

	public LCA(Graph graph, int root) {
		this.graph = graph;
		order = new long[2 * graph.vertexCount() - 1];
		position = new int[graph.vertexCount()];
		level = new int[graph.vertexCount()];
		int[] index = new int[graph.vertexCount()];
		for (int i = 0; i < index.length; i++)
			index[i] = graph.firstOutbound(i);
		int[] last = new int[graph.vertexCount()];
		int[] stack = new int[graph.vertexCount()];
		stack[0] = root;
		int size = 1;
		int j = 0;
		last[root] = -1;
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
		lcaTree = new ReadOnlyIntervalTree(order) {
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
			protected long neutralValue() {
				return -1;
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
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int to = graph.destination(i);
			if (to != last) {
				currentPosition = calculate(to, vertex, currentPosition);
				order[currentPosition++] = vertex;
			}
		}
		return currentPosition;
	}
}
