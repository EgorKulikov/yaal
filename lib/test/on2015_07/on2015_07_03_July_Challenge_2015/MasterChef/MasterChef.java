package on2015_07.on2015_07_03_July_Challenge_2015.MasterChef;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MasterChef {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int budget = in.readInt();
        int count = in.readInt();
        int[] value = IOUtils.readIntArray(in, size);
        IntervalTree tree = new LongIntervalTree(size) {
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
                return budget + 1;
            }

            @Override
            protected long neutralDelta() {
                return budget + 1;
            }
        };
        for (int i = 0; i < count; i++) {
            int from = in.readInt() - 1;
            int to = in.readInt() - 1;
            int cost = in.readInt();
            tree.update(from, to, cost);
        }
        long[] result = new long[budget + 1];
        for (int i = 0; i < size; i++) {
            if (value[i] < 0) {
                int cost = (int) tree.query(i, i);
                for (int j = budget; j >= cost; j--) {
                    result[j] = Math.max(result[j], result[j - cost] - value[i]);
                }
            }
        }
        out.printLine(ArrayUtils.sumArray(value) + result[budget]);
    }
}
