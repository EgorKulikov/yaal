package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = in.readInt();
        }
        long[] s = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            s[i] = f[i] + s[i + 1];
        }
        long[] ss = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            ss[i] = s[i] + ss[i + 1];
        }
        long[][] d = new long[m + 1][n + 1];
        int[][] q = new int[m + 1][n + 1];
        for (int j = 1; j <= m; j++) {
            if (j == 1) {
                for (int i = 1; i <= n; i++) {
                    q[j][i] = i;
                    d[j][i] = ss[0] - ss[i] - s[i] * i;
                }
            } else {
                for (int i = n; i > 0; i--) {
                    d[j][i] = Long.MAX_VALUE / 2;
                    for (int k = i == n ? 0 : q[j][i + 1] - 1; k <= q[j - 1][i]; k++) {
                        if (k < 0) continue;
                        long dd = ss[i - k] - ss[i] - s[i] * k;
                        dd += d[j - 1][i - k];
                        if (dd < d[j][i]) {
                            d[j][i] = dd;
                            q[j][i] = k;
                        }
                    }
                }
            }
        }
        out.printLine(d[m][n]);
    }
}
