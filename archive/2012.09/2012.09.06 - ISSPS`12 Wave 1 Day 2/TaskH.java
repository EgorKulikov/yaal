package net.egork;

import net.egork.collections.intervaltree.ArrayBasedLongIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		ArrayBasedLongIntervalTree tree = new ArrayBasedLongIntervalTree(new long[count]) {
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
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (type == 'A') {
				int value = in.readInt();
				tree.update(from, to, value);
			} else
				out.printLine(tree.query(from, to));
		}
	}
}
