package on2017_01.on2017_01_23_SNWS_2017_Round_3.F___Jumping_Sequence;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        IntervalTree max = new MaxTree(100001);
        IntervalTree min = new MaxTree(100001);
        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            int price = in.readInt();
            min.update(price, price, max.query(0, price - 1) + 1);
            max.update(price, price, min.query(price + 1, 100000) + 1);
        }
        out.printLine(Math.max(min.query(0, 100000), max.query(0, 100000)));
    }

    static class MaxTree extends LongIntervalTree {
        protected MaxTree(int size) {
            super(size);
        }

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
            return 0;
        }

        @Override
        protected long neutralDelta() {
            return 0;
        }
    }
}
