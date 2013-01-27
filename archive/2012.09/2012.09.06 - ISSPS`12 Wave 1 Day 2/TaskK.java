package net.egork;

import net.egork.collections.intervaltree.ArrayBasedLongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		long[] array = IOUtils.readLongArray(in, count);
		ArrayBasedLongIntervalTree tree = new ArrayBasedLongIntervalTree(array) {
			@Override
			protected long joinValue(long left, long right) {
				return left + right;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				if (delta == neutralDelta())
					return was;
				return delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				if (delta == neutralDelta())
					return value;
				return delta * length;
			}

			@Override
			protected long neutralValue() {
				return 0;
			}

			@Override
			protected long neutralDelta() {
				return Long.MIN_VALUE;
			}
		};
		tree.init();
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == '?') {
				int from = in.readInt() - 1;
				int to = in.readInt() - 1;
				out.printLine(tree.query(from, to));
			} else {
				int position = in.readInt() - 1;
				int value = in.readInt();
				tree.update(position, position, value);
			}
		}
	}
}
