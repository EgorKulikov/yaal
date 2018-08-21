package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.misc.ArrayUtils.createArray;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] a = in.readIntArray(n);
        int[] min = createArray(q + 1, n);
        int[] max = createArray(q + 1, -1);
        for (int i = 0; i < n; i++) {
            min[a[i]] = Math.min(min[a[i]], i);
            max[a[i]] = max(max[a[i]], i);
        }
        LongIntervalTree tree = new LongIntervalTree(n) {
            @Override
            protected long joinValue(long left, long right) {
                return max(left, right);
            }

            @Override
            protected long joinDelta(long was, long delta) {
                return max(was, delta);
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                return max(value, delta);
            }

            @Override
            protected long neutralValue() {
                return 0;
            }

            @Override
            protected long neutralDelta() {
                return 0;
            }
        };
        for (int i = 1; i <= q; i++) {
            if (min[i] != n) {
                tree.update(min[i], max[i], i);
            }
        }
        boolean needMax = min[q] == n;
        for (int i = 0; i < n; i++) {
            if (a[i] != 0 && a[i] < tree.query(i, i)) {
                out.printLine("NO");
                return;
            }
            if (a[i] == 0) {
                if (needMax) {
                    a[i] = q;
                    needMax = false;
                } else {
                    a[i] = (int) max(tree.query(i, i), 1);
                }
            }
        }
        if (needMax) {
            out.printLine("NO");
            return;
        }
        out.printLine("YES");
        out.printLine(a);
    }
}
