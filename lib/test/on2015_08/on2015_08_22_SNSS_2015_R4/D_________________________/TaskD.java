package on2015_08.on2015_08_22_SNSS_2015_R4.D_________________________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.readInt();
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        IOUtils.readIntArrays(in, a, b);
        int answer = 0;
        int end = 0;
        ArrayUtils.orderBy(a, b);
        for (int i = 0; i < m; i++) {
            if (a[i] > end) {
                answer = Math.max(answer, a[i] - end);
            }
            end = Math.max(end, b[i]);
        }
        answer = Math.max(answer, r - end);
        out.printLine(answer);
    }
}
