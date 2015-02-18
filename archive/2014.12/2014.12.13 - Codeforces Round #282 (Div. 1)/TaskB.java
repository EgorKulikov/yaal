package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String first = in.readString();
        String second = in.readString();
        int[] z = StringUtils.zAlgorithm(second + first);
        IntervalTree plain = new SumIntervalTree(first.length() + 1);
        IntervalTree sum = new SumIntervalTree(first.length() + 1);
        plain.update(first.length(), first.length(), 1);
        sum.update(first.length(), first.length(), first.length());
        int end = first.length() + 1;
        for (int i = first.length() - 1; i >= 0; i--) {
            if (z[second.length() + i] >= second.length()) {
                end = i + second.length();
            }
            long result = sum.query(end, first.length()) - (end - 1) * plain.query(end, first.length());
            result %= MOD;
            if (result < 0) {
                result += MOD;
            }
            sum.update(i, i, result * i % MOD);
            plain.update(i, i, result);
        }
        out.printLine(plain.query(0, first.length() - 1));
    }

    static class SumIntervalTree extends LongIntervalTree {
        protected SumIntervalTree(int size) {
            super(size);
        }

        @Override
        protected long joinValue(long left, long right) {
            return (left + right) % MOD;
        }

        @Override
        protected long joinDelta(long was, long delta) {
            return (was + delta) % MOD;
        }

        @Override
        protected long accumulate(long value, long delta, int length) {
            return (value + delta * length) % MOD;
        }

        @Override
        protected long neutralValue() {
            return 0;
        }

        @Override
        protected long neutralDelta() {
            return 0;
        }
    }
}
