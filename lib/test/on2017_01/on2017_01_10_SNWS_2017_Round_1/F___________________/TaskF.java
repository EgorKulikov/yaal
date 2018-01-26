package on2017_01.on2017_01_10_SNWS_2017_Round_1.F___________________;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.sort;
import static net.egork.misc.ArrayUtils.sumArray;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] t = in.readIntArray(n);
        sort(t);
        out.printLine(min(sumArray(t) / 2, sumArray(copyOf(t, n - 1))));
    }
}
