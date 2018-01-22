package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;
import static java.util.Arrays.fill;
import static net.egork.io.InputReader.readIntArray;

public class BinarySwaps {
    private static final int BUBEN = 300;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int t = in.readInt();
        int[] a = in.readIntArray(n);
        int[] answer = new int[n];
        LongIntervalTree tree = new LongIntervalTree(t + n) {
            @Override
            protected long joinValue(long left, long right) {
                return left + right;
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
        };
        int last = -1;
        int start = 0;
        int end = t - 1;
        int[] shifts = new int[BUBEN];
        int[] sums = new int[BUBEN];
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                if (last == -1) {
                    if (i >= t) {
                        last = i - t;
                        tree.update(0, t - 1, 1);
                        fill(shifts, 1);
                    } else {
                        last = 0;
                        tree.update(t - i, t - 1, 1);
                        fill(shifts, 0, min(i, BUBEN), 1);
                    }
                } else {
                    if (i - t > last + 1) {
                        last = i - t;
                        tree.update(start, end, 1);
                        fill(shifts, 1);
                    } else if (a[i - 1] == 1) {
                        start++;
                        end++;
                        last = (int) (i - tree.query(start, end));
                        shift(shifts);
                    } else {
                        int delta = BUBEN;
                        int res = -1;
                        int all = (int) tree.query(start, end);
                        while (true) {
                            delta = min(delta, t);
                            int left = 0;
                            int right = delta;
                            while (left < right) {
                                int middle = (left + right) >> 1;
                                int prev = (int) (last + all);
                                if (middle < BUBEN) {
                                    prev -= sums[middle];
                                } else {
                                    prev -= tree.query(end - middle + 1, end);
                                }
                                int cur = i - middle;
                                if (cur > prev + 1) {
                                    left = middle + 1;
                                } else {
                                    right = middle;
                                }
                            }
                            if (left != delta || delta == t) {
                                res = left;
                                break;
                            }
                            delta = t;
                        }
                        start++;
                        end++;
                        tree.update(end - res + 1, end, 1);
                        last = (int) (i - tree.query(start, end));
                        shift(shifts);
                        fill(shifts, 0, min(res, BUBEN), 1);
                    }
                }
                answer[last] = 1;
                for (int j = 1; j < BUBEN; j++) {
                    sums[j] = sums[j - 1] + shifts[j - 1];
                }
            }
        }
        out.printLine(answer);
    }

    private void shift(int[] shifts) {
        for (int i = BUBEN - 1; i > 0; i--) {
            shifts[i] = shifts[i - 1];
        }
        shifts[0] = 0;
    }
}
