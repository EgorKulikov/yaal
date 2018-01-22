package on2017_11.on2017_11_22_Round_58.Contiguous_Segments;


import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MAX_VALUE;
import static net.egork.misc.ArrayUtils.orderBy;

public class ContiguousSegments {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        in.readIntArrays(a, b);
        orderBy(a, b);
        int[] delta = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            delta[i] = a[i + 1] - b[i];
        }
        long answer = MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long current = 0;
            for (int j = 0; j < i; j++) {
                current += (long) (j + 1) * delta[j];
            }
            for (int j = n - 2; j >= i; j--) {
                current += (long) (n - 1 - j) * delta[j];
            }
            answer = Math.min(answer, current);
        }
        out.printLine(answer);
    }
}
