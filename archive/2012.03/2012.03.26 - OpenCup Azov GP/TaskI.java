package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
	int[] level;
	int[] order;
	private int[][] graph;
	int[] where;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		for (int i = 0; i < count - 1; i++) {
			from[i] = in.readInt() - 1;
			to[i] = in.readInt() - 1;
		}
		int[] degree = new int[count];
		for (int i : from)
			degree[i]++;
		for (int i : to)
			degree[i]++;
		graph = new int[count][];
		for (int i = 0; i < count; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < count - 1; i++) {
			graph[from[i]][--degree[from[i]]] = to[i];
			graph[to[i]][--degree[to[i]]] = from[i];
		}
		level = new int[count];
		where = new int[count];
		order = new int[2 * count - 1];
		go(0, -1, 0, 0);
		Tree tree = new Tree(order, level);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			int c = in.readInt() - 1;
			int lcmAB = tree.get(where[a], where[b]);
			int lcmAC = tree.get(where[a], where[c]);
			int target;
			if (level[lcmAB] < level[lcmAC])
				target = lcmAC;
			else if (level[lcmAB] == level[lcmAC])
				target = tree.get(where[b], where[c]);
			else
				target = lcmAB;
			int lcm = tree.get(where[c], where[target]);
			out.printLine(level[c] + level[target] - 2 * level[lcm]);
		}
	}

	private int go(int current, int last, int index, int curLevel) {
		where[current] = index;
		level[current] = curLevel;
		order[index++] = current;
		for (int i : graph[current]) {
			if (i != last) {
				index = go(i, current, index, curLevel + 1);
				order[index++] = current;
			}
		}
		return index;
	}
}

class Tree {
	int[] value;
	int length;
	private final int[] level;

	Tree(int[] array, int[] level) {
		this.level = Arrays.copyOf(level, level.length + 1);
		this.level[level.length] = Integer.MAX_VALUE;
		length = array.length;
		value = new int[4 * length];
		init(0, 0, length - 1, array);
	}

	private void init(int root, int from, int to, int[] array) {
		if (from == to) {
			value[root] = array[from];
		} else {
			int middle = (from + to) / 2;
			init(2 * root + 1, from, middle, array);
			init(2 * root + 2, middle + 1, to, array);
			if (level[value[2 * root + 1]] < level[value[2 * root + 2]])
				value[root] = value[2 * root + 1];
			else
				value[root] = value[2 * root + 2];
		}
	}

	int get(int from, int to) {
		return get(0, Math.min(from, to), Math.max(to, from), 0, length - 1);
	}

	private int get(int root, int from, int to, int left, int right) {
		if (from > right || to < left)
			return level.length - 1;
		if (from <= left && right <= to)
			return value[root];
		int middle = (left + right) / 2;
		int leftValue = get(2 * root + 1, from, to, left, middle);
		int rightValue = get(2 * root + 2, from, to, middle + 1, right);
		if (level[leftValue] < level[rightValue])
			return leftValue;
		return rightValue;
	}
}