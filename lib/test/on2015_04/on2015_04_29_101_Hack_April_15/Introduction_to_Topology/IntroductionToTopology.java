package on2015_04.on2015_04_29_101_Hack_April_15.Introduction_to_Topology;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class IntroductionToTopology {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int count = in.readInt();
        int[] from = new int[count];
        int[] to = new int[count];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);
        from = Arrays.copyOf(from, count + 1);
        to = Arrays.copyOf(to, count + 1);
        to[count++] = size - 1;
        int[] order = ArrayUtils.order(from);
        ArrayUtils.reverse(order);
        int[] toRight = ArrayUtils.createArray(size, -1);
        IntervalTree tree = new LongIntervalTree(size) {
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
                return Math.max(value, delta);
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
        for (int i : order) {
            int result = (int) Math.max(to[i], tree.query(from[i], to[i] + 1));
            toRight[from[i]] = Math.max(toRight[from[i]], result);
            tree.update(from[i], from[i], result);
        }
        int[] toLeft = ArrayUtils.createArray(size, size);
        tree = new LongIntervalTree(size) {
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
                return size;
            }

            @Override
            protected long neutralDelta() {
                return size;
            }
        };
        order = ArrayUtils.order(to);
        for (int i : order) {
            int result = (int) Math.min(from[i], tree.query(from[i] - 1, to[i]));
            toLeft[to[i]] = Math.min(toLeft[to[i]], result);
            tree.update(to[i], to[i], result);
        }
        long[] begins = new long[size];
        long[] ends = new long[size];
        for (int i = 0; i < size; i++) {
            begins[i] = 1;
            for (int j = 0; j < i - 1; j++) {
                begins[i] += ends[j];
            }
            begins[i] %= MOD;
            for (int j = toLeft[i]; j <= i; j++) {
                if (toRight[j] >= i) {
                    ends[i] += begins[j];
                }
            }
            ends[i] %= MOD;
        }
        long answer = 1;
        for (long l : ends) {
            answer += l;
        }
        out.printLine(answer % MOD);
    }
}
