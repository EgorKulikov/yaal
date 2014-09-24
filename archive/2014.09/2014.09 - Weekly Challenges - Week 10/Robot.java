package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Robot {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] value = new int[count];
		int[] energy = new int[count];
		IOUtils.readIntArrays(in, value, energy);
		IntervalTree tree = new LongIntervalTree(count) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.max(left, right);
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return was + delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return value + delta;
			}

			@Override
			protected long neutralValue() {
				return Long.MIN_VALUE / 2;
			}

			@Override
			protected long neutralDelta() {
				return 0;
			}
		};
		tree.update(count - 1, count - 1, value[count - 1] - Long.MIN_VALUE / 2);
		for (int i = count - 2; i >= 0; i--) {
			if (energy[i] > 0) {
				tree.update(i, i, tree.query(i + 1, i + energy[i]) - Long.MIN_VALUE / 2);
			}
			tree.update(i + 1, count - 1, value[i]);
		}
		out.printLine(tree.query(0, 0));
    }
}
