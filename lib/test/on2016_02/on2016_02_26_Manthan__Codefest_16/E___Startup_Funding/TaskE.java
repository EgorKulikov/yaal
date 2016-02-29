package on2016_02.on2016_02_26_Manthan__Codefest_16.E___Startup_Funding;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] v = readIntArray(in, n);
        int[] c = readIntArray(in, n);
        int[] result = new int[n];
        IntervalTree max = new ReadOnlyIntervalTree(asLong(v)) {
            @Override
            protected long neutralValue() {
                return 0;
            }

            @Override
            protected long joinValue(long left, long right) {
                return max(left, right);
            }
        };
        IntervalTree min = new ReadOnlyIntervalTree(asLong(c)) {
            @Override
            protected long neutralValue() {
                return Integer.MAX_VALUE;
            }

            @Override
            protected long joinValue(long left, long right) {
                return min(left, right);
            }
        };
        int at = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (at - 1 >= i && max.query(i, at - 1) * 100 > min.query(i, at)) {
                at--;
            }
            result[i] = (int) min(max.query(i, at) * 100, min.query(i, at));
        }
        sort(result, IntComparator.DEFAULT);
        double answer = 0;
        double last = (double)k / n;
        for (int i = 0; i <= n - k; i++) {
            answer += last * result[i];
            last /= n - 1 - i;
            last *= n - k - i;
        }
        out.printLine(answer);
    }
}
