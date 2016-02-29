package on2016_02.on2016_02_28_8VC_Venture_Cup_2016___Final_Round.C___Package_Delivery;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int d = in.readInt();
        int n = in.readInt();
        int m = in.readInt();
        int[] x = new int[m];
        int[] p = new int[m];
        readIntArrays(in, x, p);
        m++;
        x = copyOf(x, m);
        p = copyOf(p, m);
        orderBy(x, p);
        int[] poi = new int[2 * m + 1];
        for (int i = 0; i < m; i++) {
            poi[2 * i] = x[i];
            poi[2 * i + 1] = min(d, x[i] + n);
        }
        poi[2 * m] = d;
        sort(poi, IntComparator.DEFAULT);
        IntervalTree tree = new ReadOnlyIntervalTree(asLong(p)) {
            @Override
            protected long neutralValue() {
                return Long.MAX_VALUE;
            }

            @Override
            protected long joinValue(long left, long right) {
                return min(left, right);
            }
        };
        long answer = 0;
        int left = 0;
        int right = 0;
        for (int i = 1; i < poi.length; i++) {
            while (left < m && poi[i] - x[left] > n) {
                left++;
            }
            while (right + 1 < m && poi[i - 1] >= x[right + 1]) {
                right++;
            }
            if (left > right) {
                out.printLine(-1);
                return;
            }
            answer += tree.query(left, right) * (poi[i] - poi[i - 1]);
        }
        out.printLine(answer);
    }
}
