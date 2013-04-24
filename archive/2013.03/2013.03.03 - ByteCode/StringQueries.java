package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class StringQueries {
	static final long MOD = 106109099;
	long[] factorial = IntegerUtils.generateFactorial(50001, MOD);
	long[] reverse = IntegerUtils.generateReverseFactorials(50001, MOD);
	SpecialTree[] trees = new SpecialTree[26];

	{
		for (int i = 0; i < 26; i++) {
			final int finalI = i;
			trees[i] = new SpecialTree(50000, finalI);
			trees[i].init();
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		final char[] original = in.readString().toCharArray();

		for (int i = 0; i < 26; i++)
			trees[i].init();
		for (int i = 0; i < original.length; i++)
			trees[original[i] - 'a'].update(i, i, 1);
		long[] result = new long[26];
		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			char type = in.readCharacter();
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (type == 'Q') {
				int remaining = (to - from + 1) >> 1;
				int odd = 0;
				long answer = factorial[remaining];
				for (int j = 0; j < 26; j++) {
					long value = trees[j].query(from, to);
					odd += value & 1;
					answer *= reverse[((int) (value >> 1))];
					answer %= MOD;
				}
				if (odd > 1)
					out.printLine(0);
				else
					out.printLine(answer);
			} else {
				for (int j = 0; j < 26; j++) {
					result[j] = trees[j].query(from, to);
					trees[j].update(from, to, 0);
				}
				for (int j = 0; j < 26; j++) {
					trees[j].update(from, (int) (from + result[j] - 1), 1);
					from += result[j];
				}
			}
		}
//		for (int i = 0; i < original.length; i++) {
//			for (int j = 0; j < 26; j++) {
//				if (trees[j].query(i, i) == 1) {
//					out.print((char)('a' + j));
//					break;
//				}
//			}
//		}
		for (int i = 0; i < 26; i++)
			trees[i].regenerate(original);
		out.printLine(original);
    }

	private static class SpecialTree extends LongIntervalTree {
		private final int finalI;
		private char mark;

		public SpecialTree(int length, int finalI) {
			super(length);
			this.finalI = finalI;
			mark = (char) (finalI + 'a');
		}

		@Override
		protected long joinValue(long left, long right) {
			return left + right;
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
			return delta * length;
		}

		@Override
		protected long neutralValue() {
			return 0;
		}

		@Override
		protected long neutralDelta() {
			return -1;
		}

		public void regenerate(char[] original) {
			regenerate(0, 0, size - 1, original);
		}

		private void regenerate(int root, int from, int to, char[] original) {
			if (delta[root] == 0)
				return;
			if (delta[root] == 1) {
				for (int i = from; i <= to; i++)
					original[i] = mark;
				return;
			}
			if (from == to) {
				if (value[root] == 1) {
					original[from] = mark;
				}
				return;
			}
			int middle = (from + to) >> 1;
			regenerate(2 * root + 1, from, middle, original);
			regenerate(2 * root + 2, middle + 1, to, original);
		}
	}
}
