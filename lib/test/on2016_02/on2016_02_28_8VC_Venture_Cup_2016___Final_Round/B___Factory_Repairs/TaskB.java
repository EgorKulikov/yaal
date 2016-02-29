package on2016_02.on2016_02_28_8VC_Venture_Cup_2016___Final_Round.B___Factory_Repairs;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int a = in.readInt();
        int b = in.readInt();
        int q = in.readInt();
        SpecialTree aTree = new SpecialTree(n, b);
        SpecialTree bTree = new SpecialTree(n, a);
        for (int i = 0; i < q; i++) {
            int type = in.readInt();
            if (type == 1) {
                int d = in.readInt() - 1;
                int qty = in.readInt();
                aTree.update(d, d, qty);
                bTree.update(d, d, qty);
            } else {
                int p = in.readInt() - 1;
                out.printLine(aTree.query(0, p - 1) + bTree.query(p + k, n - 1));
            }
        }
    }

    static class SpecialTree extends LongIntervalTree {
        int limit;

        protected SpecialTree(int size, int limit) {
            super(size);
            this.limit = limit;
        }

        @Override
        protected long joinValue(long left, long right) {
            return left + right;
        }

        @Override
        protected long joinDelta(long was, long delta) {
            return min(was + delta, limit);
        }

        @Override
        protected long accumulate(long value, long delta, int length) {
            if (delta == 0) {
                return value;
            }
            return min(value + delta, limit);
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
