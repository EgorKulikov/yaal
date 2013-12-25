package net.egork;

import net.egork.collections.intervaltree.ArrayBasedLongIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] sequence = in.readString().toCharArray();
		long[] balance = new long[sequence.length];
		balance[0] = sequence[0] == '(' ? 1 : -1;
		for (int i = 1; i < sequence.length; i++)
			balance[i] = balance[i - 1] + (sequence[i] == '(' ? 1 : -1);
		ArrayBasedLongIntervalTree tree = new ArrayBasedLongIntervalTree(balance) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.min(left, right);
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
				return Long.MAX_VALUE / 2;
			}

			@Override
			protected long neutralDelta() {
				return 0;
			}
		};
		tree.init();
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int position = in.readInt();
			if (sequence[position] == '(') {
				sequence[position] = ')';
				tree.update(position, sequence.length - 1, -2);
			} else {
				sequence[position] = '(';
				tree.update(position, sequence.length - 1, 2);
			}
			if (tree.query(0, sequence.length - 1) == 0 && tree.query(sequence.length - 1, sequence.length - 1) == 0)
				out.printLine('+');
			else
				out.printLine('-');
		}
	}
}
