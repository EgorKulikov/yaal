package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		ZigzagIntervalTree[][] trees = new ZigzagIntervalTree[7][];
		for (int i = 2; i <= 6; i++) {
			trees[i] = new ZigzagIntervalTree[2 * i - 2];
			for (int j = 0; j < 2 * i - 2; j++)
				trees[i][j] = new ZigzagIntervalTree(array, i, j);
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				int position = in.readInt() - 1;
				int value = in.readInt();
				for (int j = 2; j <= 6; j++) {
					ZigzagIntervalTree[] row = trees[j];
					row[position % (2 * j - 2)].update(position, position, value);
				}
			} else {
				int from = in.readInt() - 1;
				int to = in.readInt() - 1;
				int z = in.readInt();
				long result = 0;
				int index = from % (2 * z - 2);
				for (int j = 0; j < z; j++) {
					result += trees[z][index++].query(from, to) * (j + 1);
					if (index == 2 * z - 2)
						index = 0;
				}
				for (int j = z; j < 2 * z - 2; j++) {
					result += trees[z][index++].query(from, to) * (2 * z - 1 - j);
					if (index == 2 * z - 2)
						index = 0;
				}
				out.printLine(result);
			}
		}
	}

	private static class ZigzagIntervalTree extends LongIntervalTree {
		private final int[] array;
		private final int z;
		private final int shift;

		protected ZigzagIntervalTree(int[] array, int z, int shift) {
			super(array.length);
			this.array = array;
			this.z = z;
			this.shift = shift - 2 * z + 2;
			init();
		}

		@Override
		protected long joinValue(long left, long right) {
			return left + right;
		}

		@Override
		protected long joinDelta(long was, long delta) {
			return delta;
		}

		@Override
		protected long accumulate(long value, long delta, int length) {
			if (delta == Long.MIN_VALUE)
				return value;
			return delta;
		}

		@Override
		protected long neutralValue() {
			return 0;
		}

		@Override
		protected long neutralDelta() {
			return Long.MIN_VALUE;
		}

		@Override
		protected long initValue(int index) {
			if ((index - shift) % (2 * z - 2) == 0)
				return array[index];
			return 0;
		}
	}
}
