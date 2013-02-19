package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DeadPixels {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int width = in.readInt();
		int height = in.readInt();
		int imageWidth = in.readInt();
		int imageHeight = in.readInt();
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		x[0] = in.readInt();
		y[0] = in.readInt();
		int a = in.readInt();
		int b = in.readInt();
		int c = in.readInt();
		int d = in.readInt();
		for (int i = 1; i < count; i++) {
			x[i] = (a * x[i - 1] + b * y[i - 1] + 1) % width;
			y[i] = (c * x[i - 1] + d * y[i - 1] + 1) % height;
		}
		IntList[] toAdd = new IntList[height];
		IntList[] toRemove = new IntList[height];
		for (int i = 0; i < height; i++) {
			toAdd[i] = new IntArrayList();
			toRemove[i] = new IntArrayList();
		}
		for (int i = 0; i < count; i++) {
			toAdd[Math.max(y[i] - imageHeight + 1, 0)].add(x[i]);
			toRemove[y[i]].add(x[i]);
		}
		IntervalTree tree = new IntervalTree(width - imageWidth + 1);
		long result = 0;
		for (int i = 0; i <= height - imageHeight; i++) {
			for (int j : toAdd[i].toArray())
				tree.update(j - imageWidth + 1, j, 1);
			result += tree.query();
			for (int j : toRemove[i].toArray())
				tree.update(j - imageWidth + 1, j, -1);
		}
		out.printLine("Case #" + testNumber + ":", result);
    }

	static class IntervalTree {
		protected int size;
		protected long[] value;
		protected long[] delta;

		protected IntervalTree(int size) {
			this.size = size;
			int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
			value = new long[nodeCount];
			delta = new long[nodeCount];
			init();
		}

		public void init() {
			init(0, 0, size - 1);
		}

		private void init(int root, int left, int right) {
			if (left == right) {
				value[root] = 1;
				delta[root] = 0;
			} else {
				int middle = (left + right) >> 1;
				init(2 * root + 1, left, middle);
				init(2 * root + 2, middle + 1, right);
				value[root] = value[2 * root + 1] + value[2 * root + 2];
				delta[root] = 0;
			}
		}

		public void update(int from, int to, long delta) {
			update(0, 0, size - 1, from, to, delta);
		}

		private void update(int root, int left, int right, int from, int to, long delta) {
			if (left > to || right < from)
				return;
			if (left >= from && right <= to) {
				this.delta[root] += delta;
				return;
			}
			int middle = (left + right) >> 1;
			update(2 * root + 1, left, middle, from, to, delta);
			update(2 * root + 2, middle + 1, right, from, to, delta);
			value[root] = getValue(2 * root + 1) + getValue(2 * root + 2);
		}

		private long getValue(int root) {
			if (delta[root] == 0)
				return value[root];
			return 0;
		}

		public long query() {
			return getValue(0);
		}
	}
}
