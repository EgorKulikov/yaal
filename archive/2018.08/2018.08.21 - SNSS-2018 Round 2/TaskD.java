package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[][] m = in.readIntTable(n, n);
        while (m[0][0] > min(m[0][n - 1], m[n - 1][0])) {
            int[][] k = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    k[i][j] = m[j][n - 1 - i];
                }
            }
            m = k;
        }
        for (int i = 0; i < n; i++) {
            out.printLine(m[i]);
        }
    }
}
