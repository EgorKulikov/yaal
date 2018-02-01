package on2018_01.on2018_01_31_CSAcademy_Round__67.D;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.orderBy;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        LongIntervalTree tree = new LongIntervalTree(100000) {
            @Override
            protected long joinValue(long left, long right) {
                if (left == neutralValue()) {
                    return right;
                }
                return left;
            }

            @Override
            protected long joinDelta(long was, long delta) {
                if (delta == neutralDelta()) {
                    return was;
                }
                return delta;
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                if (delta == neutralDelta()) {
                    return value;
                }
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

            @Override
            protected long initValue(int index) {
                return index;
            }
        };
        int n = in.readInt();
        int m = in.readInt();
        int[] x1 = new int[n];
        int[] x2 = new int[n];
        int[] y = new int[n];
        in.readIntArrays(x1, x2, y);
        decreaseByOne(x1, x2);
        orderBy(y, x1, x2);
        for (int i = 0; i < n; i++) {
            int left = min(x1[i], x2[i]);
            int right = max(x1[i], x2[i]);
            int toLeft = (left + right) >> 1;
            tree.update(left, toLeft, tree.query(left, left));
            tree.update(toLeft + 1, right, tree.query(right, right));
        }
        for (int i = 0; i < m; i++) {
            int x = in.readInt() - 1;
            out.printLine(tree.query(x, x) + 1);
        }
    }
}
