package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class CRamzesIInvertirovanieUglov {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[][] a = in.readIntTable(n, m);
        int[][] b = in.readIntTable(n, m);
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (a[i][j] != b[i][j]) {
                    a[i][j] = 1 - a[i][j];
                    a[n - 1][j] = 1 - a[n - 1][j];
                    a[i][m - 1] = 1 - a[i][m - 1];
                    a[n - 1][m - 1] = 1 - a[n - 1][m - 1];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i][m - 1] != b[i][m - 1]) {
                out.printLine("No");
                return;
            }
        }
        for (int i = 0; i < m; i++) {
            if (a[n - 1][i] != b[n - 1][i]) {
                out.printLine("No");
                return;
            }
        }
        out.printLine("Yes");
    }
}
