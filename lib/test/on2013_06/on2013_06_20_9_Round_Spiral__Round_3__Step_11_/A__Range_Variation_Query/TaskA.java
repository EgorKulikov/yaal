package on2013_06.on2013_06_20_9_Round_Spiral__Round_3__Step_11_.A__Range_Variation_Query;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		LongIntervalTree maxTree = new LongIntervalTree(100001) {
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
				return Integer.MIN_VALUE;
			}

			@Override
			protected long neutralDelta() {
				return Integer.MIN_VALUE;
			}

			@Override
			protected long initValue(int index) {
				return (long)index * index % 12345 + (long)index * index * index % 23456;
			}
		};
		LongIntervalTree minTree = new LongIntervalTree(100001) {
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
				return Integer.MAX_VALUE;
			}

			@Override
			protected long neutralDelta() {
				return Integer.MIN_VALUE;
			}

			@Override
			protected long initValue(int index) {
				return (long)index * index % 12345 + (long)index * index * index % 23456;
			}
		};
		minTree.init();
		maxTree.init();
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt();
			int to = in.readInt();
			if (from < 0) {
				minTree.update(-from, -from, to);
				maxTree.update(-from, -from, to);
			} else
				out.printLine(maxTree.query(from, to) - minTree.query(from, to));
		}
    }
}
