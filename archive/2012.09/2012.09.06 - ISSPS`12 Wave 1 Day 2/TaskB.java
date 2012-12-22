package net.egork;

import net.egork.collections.intervaltree.ArrayBasedLongIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long[] array = new long[100000];
		for (long i = 1; i <= 100000; i++)
			array[((int) (i - 1))] = i * i % 12345 + i * i * i % 23456;
		ArrayBasedLongIntervalTree maxTree = new ArrayBasedLongIntervalTree(array) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.max(left, right);
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
				return delta;
			}

			@Override
			protected long neutralValue() {
				return Long.MIN_VALUE;
			}

			@Override
			protected long neutralDelta() {
				return Long.MIN_VALUE;
			}
		};
		ArrayBasedLongIntervalTree minTree = new ArrayBasedLongIntervalTree(array) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.min(left, right);
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
				return delta;
			}

			@Override
			protected long neutralValue() {
				return Long.MAX_VALUE;
			}

			@Override
			protected long neutralDelta() {
				return Long.MAX_VALUE;
			}
		};
		maxTree.init();
		minTree.init();
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			if (from < 0) {
				from = -from - 2;
				int value = in.readInt();
				minTree.update(from, from, value);
				maxTree.update(from, from, value);
			} else {
				int to = in.readInt() - 1;
				out.printLine(maxTree.query(from, to) - minTree.query(from, to));
			}
		}
	}
}
