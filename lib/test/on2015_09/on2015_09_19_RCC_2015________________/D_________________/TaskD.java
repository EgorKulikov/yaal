package on2015_09.on2015_09_19_RCC_2015________________.D_________________;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	int[] y;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] x = new int[n];
		y = new int[n];
		IOUtils.readIntArrays(in, x, y);
		ArrayUtils.compress(x);
		ArrayUtils.compress(y);
		int[] order = ArrayUtils.createOrder(n);
		ArrayUtils.sort(order, (a, b) -> y[a] != y[b] ? y[a] - y[b] : x[a] - x[b]);
		IntervalTree tree = new LongIntervalTree(n) {
			@Override
			protected long joinValue(long left, long right) {
				if (getVal(left) > getVal(right)) {
					return left;
				} else {
					return right;
				}
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return joinValue(was, delta);
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return joinValue(value, delta);
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
		int lastY = -1;
		int lastX = -1;
		long lastDown = -1;
		for (int i : order) {
			long curDown = tree.query(x[i], x[i]);
			if (getVal(lastY) != y[i]) {
				long toRight = tree.query(lastX + 1, n - 1);
				if (getVal(toRight) > getVal(lastDown)) {
					out.printLine("NO");
					for (int j = lastX + 1; j < n; j++) {
						long query = tree.query(j, j);
						if (getVal(toRight) == getVal(query)) {
							toRight = query;
							break;
						}
					}
					out.printLine(toRight + 1, lastY + 1);
					return;
				}
				long toLeft = tree.query(0, x[i] - 1);
				if (getVal(toLeft) > getVal(curDown)) {
					out.printLine("NO");
					for (int j = x[i] - 1; j >= 0; j--) {
						long query = tree.query(j, j);
						if (getVal(toLeft) == getVal(query)) {
							toLeft = query;
							break;
						}
					}
					out.printLine(toLeft + 1, i + 1);
					return;
				}
			} else {
				long inBetween = tree.query(lastX + 1, x[i] - 1);
				if (getVal(inBetween) > Math.min(getVal(lastDown), getVal(curDown))) {
					long other = getVal(lastDown) > getVal(curDown) ? i : lastY;
					out.printLine("NO");
					if (other == lastY) {
						for (int j = lastX + 1; j < x[i]; j++) {
							long query = tree.query(j, j);
							if (getVal(inBetween) == getVal(query)) {
								inBetween = query;
								break;
							}
						}
					} else {
						for (int j = x[i] - 1; j >= lastX + 1; j--) {
							long query = tree.query(j, j);
							if (getVal(inBetween) == getVal(query)) {
								inBetween = query;
								break;
							}
						}
					}
					out.printLine(other + 1, inBetween + 1);
					return;
				}
			}
			lastY = i;
			lastX = x[i];
			lastDown = curDown;
			tree.update(x[i], x[i], i);
		}
		long toRight = tree.query(lastX + 1, n - 1);
		if (getVal(toRight) > getVal(lastDown)) {
			out.printLine("NO");
			for (int j = lastX + 1; j < n; j++) {
				long query = tree.query(j, j);
				if (getVal(toRight) == getVal(query)) {
					toRight = query;
					break;
				}
			}
			out.printLine(toRight + 1, lastY + 1);
			return;
		}
		out.printLine("YES");
	}

	int getVal(long at) {
		if (at == -1) {
			return -1;
		}
		return y[((int) at)];
	}
}
