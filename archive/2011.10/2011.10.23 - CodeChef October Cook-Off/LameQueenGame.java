package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class LameQueenGame {

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int lameness = in.readInt();
		int[] diagonal = new int[200000];
		boolean[] row = new boolean[200000];
		int fullDiagonal = 0;
		int all = (1 << lameness) - 1;
		int[] x = new int[200000];
		int[] y = new int[200000];
		int count = 0;
//		long time = System.currentTimeMillis();
		for (int j = 0; j < 200000; j++) {
			if (row[j])
				continue;
			int shift = j % lameness;
			for (int k = j + fullDiagonal; k < 200000; k++) {
				if (!row[k] && (diagonal[k - j] >> shift & 1) == 0) {
					row[k] = true;
					row[j] = true;
					x[count] = j;
					y[count++] = k;
//					tree.add(j, k);
					diagonal[k - j] += 1 << shift;
					while (k < 200000 && k - j == fullDiagonal && diagonal[k - j] == all) {
						k++;
						fullDiagonal++;
					}
					break;
				}
			}
		}
//		System.err.println(System.currentTimeMillis()- );
		IntervalTree tree = new IntervalTree(x, y, count);
		int testCount = in.readInt();
		for (int i = 0; i < testCount; i++) {
			int x1 = in.readInt() - 1;
			int y1 = in.readInt() - 1;
			int x2 = in.readInt();
			int y2 = in.readInt();
			int delta = Math.max(Math.min(lameness, Math.min(x2, y2)) - Math.max(x1, y1), 0);
			out.println(new Rational(tree.get(x1, x2, y1, y2) + tree.get(y1, y2, x1, x2) - delta, (long)(x2 - x1) * (y2 - y1)));
		}
	}
}

class IntervalTree {
	private static final int SIZE = Integer.highestOneBit(200000) << 2;

	private int[] from = new int[SIZE];
	private int[] to = new int[SIZE];
	private int[][] value = new int[SIZE][];

	IntervalTree(int[] x, int[] y, int count) {
		int[] indices = new int[count];
		for (int i = 0; i < count; i++)
			indices[i] = i;
		initTree(0, 200000, 0, x, y, indices);
	}

	private void initTree(int from, int to, int root, int[] x, int[] y, int[] indices) {
		this.from[root] = from;
		this.to[root] = to;
		this.value[root] = new int[indices.length];
		for (int i = 0; i < indices.length; i++)
			value[root][i] = y[indices[i]];
		Arrays.sort(value[root]);
		if (to - from > 1) {
			int middle = (from + to) / 2;
			int leftCount = 0;
			int rightCount = 0;
			for (int i : indices) {
				if (x[i] >= middle)
					rightCount++;
				else
					leftCount++;
			}
			int[] leftIndices = new int[leftCount];
			int[] rightIndices = new int[rightCount];
			int leftIndex = 0;
			int rightIndex = 0;
			for (int i : indices) {
				if (x[i] >= middle)
					rightIndices[rightIndex++] = i;
				else
					leftIndices[leftIndex++] = i;
			}
			initTree(from, middle, 2 * root + 1, x, y, leftIndices);
			initTree(middle, to, 2 * root + 2, x, y, rightIndices);
		}
	}

	int get(int fromX, int toX, int fromY, int toY) {
		return get(fromX, toX, fromY, toY, 0);
	}

	private int get(int fromX, int toX, int fromY, int toY, int root) {
		if (fromX >= to[root] || toX <= from[root])
			return 0;
		if (fromX <= from[root] && toX >= to[root]) {
			int leftPos = Arrays.binarySearch(value[root], fromY);
			if (leftPos < 0)
				leftPos = -leftPos - 1;
			int rightPos = Arrays.binarySearch(value[root], toY);
			if (rightPos < 0)
				rightPos = -rightPos - 1;
			return rightPos - leftPos;
		}
		return get(fromX, toX, fromY, toY, 2 * root + 1) + get(fromX, toX, fromY, toY, 2 * root + 2);
	}

}

//class SubTree {
//	static int totalMemory;
//
//	private int[] left = new int[1];
//	private int[] right = new int[1];
//	private int[] from = new int[1];
//	private int[] to = new int[1];
//	private int[] value = new int[1];
//	private int length = 1;
//
//	SubTree() {
//		from[0] = 0;
//		to[0] = 200000;
//		left[0] = -1;
//		right[0] = -1;
//	}
//
//	void add(int y) {
//		add(y, 0, 0, 200000);
//	}
//
//	private int add(int y, int root, int from, int to) {
//		if (root == -1) {
//			ensureCapacity();
//			left[length] = -1;
//			right[length] = -1;
//			this.from[length] = from;
//			this.to[length] = to;
//			value[length] = 0;
//			root = length++;
//			totalMemory++;
//		}
//		value[root]++;
//		if (to - from == 1)
//			return root;
//		int middle = (from + to) / 2;
//		if (y < middle)
//			left[root] = add(y, left[root], from, middle);
//		else
//			right[root] = add(y, right[root], middle, to);
//		return root;
//	}
//
//	int get(int from, int to) {
//		return get(from, to, 0);
//	}
//
//	private int get(int from, int to, int root) {
//		if (root == -1)
//			return 0;
//		if (from >= this.to[root] || to <= this.from[root])
//			return 0;
//		if (from <= this.from[root] && to >= this.to[root])
//			return value[root];
//		return get(from, to, left[root]) + get(from, to, right[root]);
//	}
//
//	private void ensureCapacity() {
//		if (left.length == length) {
//			left = Arrays.copyOf(left, 2 * length);
//			right = Arrays.copyOf(right, 2 * length);
//			from = Arrays.copyOf(from, 2 * length);
//			to = Arrays.copyOf(to, 2 * length);
//			value = Arrays.copyOf(value, 2 * length);
//		}
//	}
//}