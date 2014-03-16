package on2014_01.on2014_01_12_Codeforces_Round__223__Div__1_.C___Sereja_and_Brackets;



import net.egork.collections.intervaltree.ArrayBasedIntervalTree;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] sequence = in.readString().toCharArray();
		int count = sequence.length + 1;
		long[] level = new long[count];
		for (int i = 0; i < count - 1; i++) {
			if (sequence[i] == '(')
				level[i + 1] = level[i] + 1;
			else
				level[i + 1] = level[i] - 1;
		}
		IntervalTree tree = new ArrayBasedIntervalTree(level) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.min(left, right);
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return Math.min(was, delta);
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return Math.min(value, delta);
			}

			@Override
			protected long neutralValue() {
				return Integer.MAX_VALUE;
			}

			@Override
			protected long neutralDelta() {
				return Integer.MAX_VALUE;
			}
		};
		int[] qtyClose = new int[count];
		for (int i = 0; i < count - 1; i++) {
			if (sequence[i] == ')')
				qtyClose[i + 1] = qtyClose[i] + 1;
			else
				qtyClose[i + 1] = qtyClose[i];
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt();
			long answer = qtyClose[to] - qtyClose[from] - level[from] + tree.query(from, to);
			out.printLine(answer * 2);
		}
    }
}
