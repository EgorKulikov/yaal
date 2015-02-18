package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Marathon {
    int[] x, y;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int queryCount = in.readInt();
        x = new int[count];
        y = new int[count];
        IOUtils.readIntArrays(in, x, y);
        IntervalTree distance = new SumIntervalTree(count);
        for (int i = 0; i < count - 1; i++) {
            distance.update(i, i, delta(i, i + 1));
        }
        IntervalTree skip = new LongIntervalTree(count) {
            @Override
            protected long joinValue(long left, long right) {
                return Math.max(left, right);
            }

            @Override
            protected long joinDelta(long was, long delta) {
                return Math.max(was, delta);
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                if (delta != neutralDelta()) {
                    return delta;
                }
                return value;
            }

            @Override
            protected long neutralValue() {
                return 0;
            }

            @Override
            protected long neutralDelta() {
                return -1;
            }
        };
        for (int i = 1; i < count - 1; i++) {
            skip.update(i, i, delta(i, i + 1) + delta(i - 1, i) - delta(i - 1, i + 1));
        }
        for (int i = 0; i < queryCount; i++) {
            char type = in.readCharacter();
            if (type == 'Q') {
                int from = in.readInt() - 1;
                int to = in.readInt() - 1;
                long answer = distance.query(from, to - 1) - skip.query(from + 1, to - 1);
                out.printLine(answer);
            } else {
                int at = in.readInt() - 1;
                if (at > 0) {
                    distance.update(at - 1, at - 1, -delta(at - 1, at));
                }
                if (at < count - 1) {
                    distance.update(at, at, -delta(at, at + 1));
                }
                x[at] = in.readInt();
                y[at] = in.readInt();
                if (at > 0) {
                    distance.update(at - 1, at - 1, delta(at - 1, at));
                }
                if (at < count - 1) {
                    distance.update(at, at, delta(at, at + 1));
                }
                for (int j = at - 1; j <= at + 1; j++) {
                    if (j > 0 && j < count - 1) {
                        skip.update(j, j, delta(j, j + 1) + delta(j - 1, j) - delta(j - 1, j + 1));
                    }
                }
            }
        }
    }

    protected int delta(int from, int to) {
        return Math.abs(x[from] - x[to]) + Math.abs(y[from] - y[to]);
    }
}
