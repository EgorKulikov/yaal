package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] x = new int[m];
        int[] y = new int[m];
        IOUtils.readIntArrays(in, x, y);
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                boolean ok = true;
                for (int k = 0; k < m; k++) {
                    if (x[k] == i || y[k] == j || Math.abs(x[k] - i) == Math.abs(y[k] - j)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    answer++;
                }
            }
        }
        out.printLine(answer);
    }
}
