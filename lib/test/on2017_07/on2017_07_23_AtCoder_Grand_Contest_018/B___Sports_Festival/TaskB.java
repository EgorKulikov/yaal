package on2017_07.on2017_07_23_AtCoder_Grand_Contest_018.B___Sports_Festival;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.maxPosition;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[][] a = in.readIntTable(n, m);
        decreaseByOne(a);
        int[] qty = new int[m];
        int[] at = new int[n];
        boolean[] forbidden = new boolean[m];
        int answer = n + 1;
        for (int i = 0; i < m; i++) {
            fill(qty, 0);
            for (int j = 0; j < n; j++) {
                qty[a[j][at[j]]]++;
            }
            int bad = maxPosition(qty);
            answer = Math.min(answer, qty[bad]);
            forbidden[bad] = true;
            for (int j = 0; j < n; j++) {
                while (at[j] < m && forbidden[a[j][at[j]]]) {
                    at[j]++;
                }
            }
        }
        out.printLine(answer);
    }
}
