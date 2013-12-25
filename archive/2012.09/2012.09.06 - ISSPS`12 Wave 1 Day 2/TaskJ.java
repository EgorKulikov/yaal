package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SimpleIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] values = IOUtils.readIntArray(in, count);
		final State neutral = new State(0, Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2);
		IntervalTree<State, Object> tree = new SimpleIntervalTree<State, Object>(count) {
			@Override
			protected State joinValue(State left, State right) {
				return new State(left.sum + right.sum, Math.max(Math.max(left.max, right.max), left.right + right.left),
					Math.max(left.left, left.sum + right.left), Math.max(right.right, right.sum + left.right));
			}

			@Override
			protected Object joinDelta(Object was, Object delta) {
				return null;
			}

			@Override
			protected State accumulate(State value, Object delta, int length) {
				return value;
			}

			@Override
			protected State neutralValue() {
				return neutral;
			}

			@Override
			protected Object neutralDelta() {
				return null;
			}

			@Override
			protected State initValue(int index) {
				return new State(values[index], values[index], values[index], values[index]);
			}
		};
		tree.init();
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			out.printLine(tree.query(from, to).max);
		}
	}

	static class State {
		final int sum, max, left, right;

		State(int sum, int max, int left, int right) {
			this.sum = sum;
			this.max = max;
			this.left = left;
			this.right = right;
		}
	}
}
