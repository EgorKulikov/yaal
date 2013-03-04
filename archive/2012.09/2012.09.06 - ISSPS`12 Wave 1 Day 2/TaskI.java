package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		LongIntervalTree tree = new LongIntervalTree(count) {
			@Override
			protected long joinValue(long left, long right) {
				return left + right;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return was ^ delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				if (delta == 0)
					return value;
				return length - value;
			}

			@Override
			protected long neutralValue() {
				return 0;
			}

			@Override
			protected long neutralDelta() {
				return 0;
			}
		};
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (type == 0)
				tree.update(from, to, 1);
			else
				out.printLine(tree.query(from, to));
		}
	}
}
