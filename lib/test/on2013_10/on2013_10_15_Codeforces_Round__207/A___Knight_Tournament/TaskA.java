package on2013_10.on2013_10_15_Codeforces_Round__207.A___Knight_Tournament;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int boutCount = in.readInt();
		int[] from = new int[boutCount];
		int[] to = new int[boutCount];
		int[] winner = new int[boutCount];
		IOUtils.readIntArrays(in, from, to, winner);
		MiscUtils.decreaseByOne(from, to, winner);
		IntervalTree tree = new LongIntervalTree(count) {
			@Override
			protected long joinValue(long left, long right) {
				if (left == -1)
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
		for (int i = boutCount - 1; i >= 0; i--) {
			tree.update(from[i], winner[i] - 1, winner[i]);
			tree.update(winner[i] + 1, to[i], winner[i]);
		}
		int[] answer = new int[count];
		for (int i = 0; i < count; i++)
			answer[i] = (int) (tree.query(i, i) + 1);
		out.printLine(answer);
    }
}
