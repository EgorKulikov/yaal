package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	private int[][] ascendant;
	private IntervalTree tree;
	private int[] start;
	private int[] end;
	private int[] level;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] parent = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(parent);
		int queryCount = in.readInt();
		int[] answer = new int[queryCount];
		int[] query = new int[queryCount];
		int[] power = new int[queryCount];
		IOUtils.readIntArrays(in, query, power);
		MiscUtils.decreaseByOne(query);
		int[][] graph = new int[count][];
		int[] degree = new int[count];
		for (int i : parent) {
			if (i != -1)
				degree[i]++;
		}
		for (int i = 0; i < count; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < count; i++) {
			if (parent[i] != -1)
				graph[parent[i]][--degree[parent[i]]] = i;
		}
		int[] order = new int[count];
		start = new int[count];
		end = new int[count];
		int offset = 0;
		level = new int[count];
		for (int i = 0; i < count; i++) {
			if (parent[i] == -1) {
				offset = enumerate(graph, order, start, i, offset, level, 0, end);
			}
		}
		if (offset != count)
			throw new RuntimeException();
		tree = new IntervalTree(order);
		ascendant = new int[Integer.bitCount(Integer.highestOneBit(count) - 1) + 1][count];
		ascendant[0] = parent;
		for (int i = 1; i < ascendant.length; i++) {
			for (int j = 0; j < count; j++) {
				if (ascendant[i - 1][j] == -1)
					ascendant[i][j] = -1;
				else
					ascendant[i][j] = ascendant[i - 1][ascendant[i - 1][j]];
			}
		}
		for (int i = 0; i < queryCount; i++) {
			answer[i] = Math.max(0, query(query[i], power[i], level[query[i]], power[i]) - query(query[i], power[i] - 1, level[query[i]], power[i] - 1));
		}
		out.printLine(Array.wrap(answer).toArray());
	}

	private int query(int vertex, int power, int level, int initialPower) {
		if (vertex == -1)
			return 0;
		if (power == 0) {
			if (level - this.level[vertex] != initialPower)
				throw new RuntimeException();
			return tree.query(start[vertex], end[vertex], level);
		}
		return query(ascendant[Integer.bitCount(Integer.highestOneBit(power) - 1)][vertex], power - Integer.highestOneBit(power), level, initialPower);
	}

	private int enumerate(int[][] graph, int[] order, int[] start, int current, int offset, int[] level, int curLevel, int[] end) {
		order[offset] = curLevel;
		level[current] = curLevel;
		start[current] = offset++;
		for (int i : graph[current])
			offset = enumerate(graph, order, start, i, offset, level, curLevel + 1, end);
		end[current] = offset - 1;
		return offset;
	}
}

class IntervalTree {
	protected int size;
	protected int[][] value;

	protected IntervalTree(int[] array) {
		this.size = array.length;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		value = new int[nodeCount][];
		init(array);
	}

	public void init(int[] array) {
		init(0, 0, size - 1, array);
	}

	private void init(int root, int left, int right, int[] array) {
		if (left == right) {
			value[root] = new int[]{array[left] * 2};
		} else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, array);
			init(2 * root + 2, middle + 1, right, array);
			value[root] = new int[right - left + 1];
			System.arraycopy(value[2 * root + 1], 0, value[root], 0, value[2 * root + 1].length);
			System.arraycopy(value[2 * root + 2], 0, value[root], value[2 * root + 1].length, value[2 * root + 2].length);
			if (value[root].length != value[2 * root + 1].length + value[2 * root + 2].length)
				throw new RuntimeException();
			ArrayUtils.sort(value[root], IntComparator.DEFAULT);
		}
	}

	public int query(int from, int to, int value) {
		return query(0, 0, size - 1, from, to, value);
	}

	private int query(int root, int left, int right, int from, int to, int value) {
		if (left > to || right < from)
			return 0;
		if (left >= from && right <= to)
			return Arrays.binarySearch(this.value[root], 2 * value - 1) - Arrays.binarySearch(this.value[root], 2 * value + 1);
		int middle = (left + right) >> 1;
		return query(2 * root + 1, left, middle, from, to, value) + query(2 * root + 2, middle + 1, right, from, to, value);
	}
}
