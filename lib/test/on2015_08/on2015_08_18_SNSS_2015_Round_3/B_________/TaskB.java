package on2015_08.on2015_08_18_SNSS_2015_Round_3.B_________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    int n;
    int[] f;
    int[] p;
    int[][][][] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
//        n = 3000;
        f = IOUtils.readIntArray(in, n);
//        Random r = new Random(239);
//        f = new int[n];
//        for (int i = 0; i < n; i++) {
//            f[i] = r.nextInt(100000) + 1;
//        }
        int m = in.readInt();
//        int m = 100;
        p = IOUtils.readIntArray(in, m);
//        p = new int[m];
//        for (int i = 0; i < m; i++) {
//            p[i] = r.nextInt(100000) + 1;
//        }
        Arrays.sort(p);
        answer = new int[2][n + 1][m + 1][m + 1];
        ArrayUtils.fill(answer, -1);
        out.printLine(go(0, 0, 0, m));
    }

    private int go(int taken, int pos, int left, int right) {
        if (answer[taken][pos][left][right] != -1) {
            return answer[taken][pos][left][right];
        }
        int result = 0;
        if (left < right) {
            if (taken == 1) {
                result = Math.max(result, go(0, pos, left + 1, right));
            } else {
                result = Math.max(result, go(1, pos, left, right - 1) + p[right - 1]);
            }
        }
        if (pos < n) {
            if (taken == 0) {
                result = Math.max(result, go(1, pos + 1, left, right) + f[pos]);
            }
            result = Math.max(result, go(0, pos + 1, left, right));
        }
        return answer[taken][pos][left][right] = result;
    }
}
