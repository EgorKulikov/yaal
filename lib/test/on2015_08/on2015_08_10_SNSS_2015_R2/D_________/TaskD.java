package on2015_08.on2015_08_10_SNSS_2015_R2.D_________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] x = new int[n];
        int[] h = new int[n];
        IOUtils.readIntArrays(in, x, h);
        ArrayUtils.orderBy(x, h);
        int at = -Arrays.binarySearch(x, k) - 2;
        int answer = 0;
        if (at >= 0) {
            int toLeft = x[at] - h[at];
            answer++;
            for (int i = at - 1; i >= 0; i--) {
                if (toLeft <= x[i]) {
                    toLeft = Math.min(toLeft, x[i] - h[i]);
                    answer++;
                } else {
                    break;
                }
            }
        }
        if (at + 1 < n) {
            int toRight = x[at + 1] + h[at + 1];
            answer++;
            for (int i = at + 2; i < n; i++) {
                if (toRight >= x[i]) {
                    toRight = Math.max(toRight, x[i] + h[i]);
                    answer++;
                } else {
                    break;
                }
            }
        }
        out.printLine(answer);
    }
}
