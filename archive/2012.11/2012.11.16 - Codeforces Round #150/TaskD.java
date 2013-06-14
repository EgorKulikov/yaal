package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int vx = in.readInt();
		int vy = in.readInt();
		int[][] height = IOUtils.readIntTable(in, count, count);
		final int[][] distance = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				distance[i][j] = Math.min(Math.min(vx * i + vy * j, vx * i + vy * (j + 1)), Math.min(vx * (i + 1) + vy * j, vx * (i + 1) + vy * (j + 1)));
		}
		int[] positions = new int[(count + 1) * (count + 1)];
		for (int i = 0; i <= count; i++) {
			for (int j = 0; j <= count; j++)
				positions[i * (count + 1) + j] = vy * i - vx * j;
		}
		ArrayUtils.sort(positions, IntComparator.DEFAULT);
		positions = ArrayUtils.unique(positions);
		IntervalTree tree = new IntervalTree(positions.length - 1);
		int[] order = new int[count * count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				order[i * count + j] = (i << 10) + j;
		}
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return distance[first >> 10][first & 1023] - distance[second >> 10][second & 1023];
			}
		});
		long answer = 0;
		for (int i : order) {
			int x = i >> 10;
			int y = i & 1023;
			int from = Math.min(Math.min(vy * x - vx * y, vy * x - vx * (y + 1)), Math.min(vy * (x + 1) - vx * y, vy * (x + 1) - vx * (y + 1)));
			int to = Math.max(Math.max(vy * x - vx * y, vy * x - vx * (y + 1)), Math.max(vy * (x + 1) - vx * y, vy * (x + 1) - vx * (y + 1)));
			from = Arrays.binarySearch(positions, from);
			to = Arrays.binarySearch(positions, to);
			long min = tree.query(from, to - 1);
			answer += Math.max(0, height[x][y] - min);
			tree.update(from, to - 1, height[x][y]);
		}
		out.printLine(answer);
	}

	static class IntervalTree {
		protected int size;
		protected int[] value;
		protected int[] delta;

		protected IntervalTree(int size) {
			this.size = size;
			int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
			value = new int[nodeCount];
			delta = new int[nodeCount];
		}

		public void update(int from, int to, int delta) {
			update(0, 0, size - 1, from, to, delta);
		}

		private void update(int root, int left, int right, int from, int to, int delta) {
			if (left > to || right < from)
				return;
			if (left >= from && right <= to) {
				this.delta[root] = Math.max(this.delta[root], delta);
				value[root] = Math.max(value[root], delta);
				return;
			}
			this.delta[2 * root + 1] = Math.max(this.delta[2 * root + 1], this.delta[root]);
			this.delta[2 * root + 2] = Math.max(this.delta[2 * root + 2], this.delta[root]);
			int middle = (left + right) >> 1;
			value[2 * root + 1] = Math.max(value[2 * root + 1], this.delta[root]);
			value[2 * root + 2] = Math.max(value[2 * root + 2], this.delta[root]);
			this.delta[root] = 0;
			update(2 * root + 1, left, middle, from, to, delta);
			update(2 * root + 2, middle + 1, right, from, to, delta);
			value[root] = Math.min(value[2 * root + 1], value[2 * root + 2]);
		}

		public int query(int from, int to) {
			return query(0, 0, size - 1, from, to);
		}

		private int query(int root, int left, int right, int from, int to) {
			if (left > to || right < from)
				return Integer.MAX_VALUE;
			if (left >= from && right <= to)
				return value[root];
			delta[2 * root + 1] = Math.max(delta[2 * root + 1], delta[root]);
			delta[2 * root + 2] = Math.max(delta[2 * root + 2], delta[root]);
			int middle = (left + right) >> 1;
			value[2 * root + 1] = Math.max(value[2 * root + 1], delta[root]);
			value[2 * root + 2] = Math.max(value[2 * root + 2], delta[root]);
			this.delta[root] = 0;
			return Math.min(query(2 * root + 1, left, middle, from, to), query(2 * root + 2, middle + 1, right, from, to));
		}
	}
}
