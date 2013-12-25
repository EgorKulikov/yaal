package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] a = IOUtils.readIntArray(in, count);
		int[] b = IOUtils.readIntArray(in, count);
		LongIntervalTree tree = new LongIntervalTree(count) {
			@Override
			protected long joinValue(long left, long right) {
				if (left == neutralValue())
					return right;
				return left;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				if (delta == -1)
					return was;
				return delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				if (delta == -1)
					return value;
				return delta;
			}

			@Override
			protected long neutralValue() {
				return -1;
			}

			@Override
			protected long neutralDelta() {
				return -1;
			}
		};
		tree.init();
		long mask = (1L << 32) - 1;
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				long aPosition = in.readInt() - 1;
				int bPosition = in.readInt() - 1;
				int length = in.readInt();
				tree.update(bPosition, bPosition + length - 1, (aPosition << 32) + bPosition);
			} else {
				int index = in.readInt() - 1;
				long value = tree.query(index, index);
				if (value == -1)
					out.printLine(b[index]);
				else
					out.printLine(a[((int) (index - (value & mask) + (value >> 32)))]);
			}
		}
    }
}
