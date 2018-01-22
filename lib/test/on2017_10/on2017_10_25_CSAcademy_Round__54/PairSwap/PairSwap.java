package on2017_10.on2017_10_25_CSAcademy_Round__54.PairSwap;


import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.asLong;

public class PairSwap {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        ReadOnlyIntervalTree tree = new ReadOnlyIntervalTree(asLong(a)) {
            @Override
            protected long neutralValue() {
                return MAX_VALUE;
            }

            @Override
            protected long joinValue(long left, long right) {
                return min(left, right);
            }
        };
        for (int i = 0; i < n; i++) {
            long value = tree.query(i + 1, i + k);
            if (value < a[i]) {
                int at = -1;
                for (int j = min(i + k, n - 1); j > i; j--) {
                    if (a[j] == value) {
                        at = j;
                        break;
                    }
                }
                a[at] = a[i];
                a[i] = (int) value;
                break;
            }
        }
        out.printLine(a);
    }
}
