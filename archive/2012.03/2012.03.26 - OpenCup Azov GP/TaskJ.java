package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskJ {


	private Tree tree;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.readInt();
		}
		int MAX = 100000;
		int[] pos = new int[MAX + 1];
		int[] next = new int[n];
		Arrays.fill(pos, n);
		for (int i = n - 1; i >= 0; i--) {
			next[i] = pos[a[i]];
			pos[a[i]] = i;
		}
		int[] nextneq = new int[n];
		nextneq[n - 1] = n;
		for (int i = n - 2; i >= 0; i--) {
			if (next[i] + 1 == next[i + 1]) {
				nextneq[i] = nextneq[i + 1];
			} else {
				nextneq[i] = i + 1;
			}
		}
		tree = new Tree(next);
		int queries = in.readInt();
		for (int query = 0; query < queries; query++) {
			int from = in.readInt() - 1;
			int to = in.readInt();
			if (minnext(from, to) >= to) {
				out.print(1);
				continue;
			}
			if (next[from] >= to) {
				out.print(0);
				continue;
			}
			int p = next[from] - from;
			if (nextneq[from] < to - p) {
				out.print(0);
				continue;
			}
			if (minnext(to - p, to) >= to) {
				out.print(1);
				continue;
			}
			out.print(0);
			continue;
		}
		out.printLine();
	}

	private int minnext(int from, int to) {
		return tree.get(from, to - 1);
	}
}

class Tree {
	int[] value;
	int length;

	Tree(int[] array) {
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
			value[root] = Math.min(value[2 * root + 1], value[2 * root + 2]);
		}
	}

	int get(int from, int to) {
		return get(0, from, to, 0, length - 1);
	}

	private int get(int root, int from, int to, int left, int right) {
		if (from > right || to < left)
			return Integer.MAX_VALUE;
		if (from <= left && right <= to)
			return value[root];
		int middle = (left + right) / 2;
		return Math.min(get(2 * root + 1, from, to, left, middle), get(2 * root + 2, from, to, middle + 1, right));
	}
}