package on2015_12.on2015_12_14_USACO_2015_December_Contest__Platinum.Problem_3__Counting_Haybales;



import net.egork.collections.intervaltree.ArrayBasedIntervalTree;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Haybales {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int q = in.readInt();
		int[] hay = IOUtils.readIntArray(in, n);
		IntervalTree minTree = new ArrayBasedIntervalTree(ArrayUtils.asLong(hay)) {
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
				return Long.MAX_VALUE;
			}

			@Override
			protected long neutralDelta() {
				return 0;
			}
		};
		IntervalTree sumTree = new SumIntervalTree(ArrayUtils.asLong(hay));
		for (int i = 0; i < q; i++) {
			char type = in.readCharacter();
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			if (type == 'M') {
				out.printLine(minTree.query(a, b));
			} else if (type == 'S') {
				out.printLine(sumTree.query(a, b));
			} else {
				int c = in.readInt();
				minTree.update(a, b, c);
				sumTree.update(a, b, c);
			}
		}
	}
}
