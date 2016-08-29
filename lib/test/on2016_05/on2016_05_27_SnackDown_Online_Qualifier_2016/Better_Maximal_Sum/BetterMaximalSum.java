package on2016_05.on2016_05_27_SnackDown_Online_Qualifier_2016.Better_Maximal_Sum;



import net.egork.generated.collections.list.IntArray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.maxElement;

public class BetterMaximalSum {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        if (new IntArray(a).allOf(x -> x < 0)) {
            out.printLine(maxElement(a));
            return;
        }
        long[] maxLeft = new long[n + 1];
        for (int i = 0; i < n; i++) {
            maxLeft[i + 1] = max(0, maxLeft[i] + a[i]);
        }
        long answer = maxElement(maxLeft);
        long[] maxRight = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            maxRight[i] = max(0, maxRight[i + 1] + a[i]);
        }
        for (int i = 0; i < n; i++) {
            answer = max(answer, maxLeft[i] + maxRight[i + 1]);
        }
        out.printLine(answer);
    }
}
