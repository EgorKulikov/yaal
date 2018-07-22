package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.count;
import static net.egork.misc.ArrayUtils.fill;

public class ORMatrix {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[][] a = in.readTable(n, m);
        boolean[] row = new boolean[n];
        boolean[] column = new boolean[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == '1') {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        int[][] answer = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == '0') {
                    if (row[i] || column[j]) {
                        answer[i][j] = 1;
                    } else {
                        answer[i][j] = 2;
                    }
                }
            }
        }
        if (count(row, false) == n) {
            fill(answer, -1);
        }
        for (int[] r : answer) {
            out.printLine(r);
        }
    }
}
