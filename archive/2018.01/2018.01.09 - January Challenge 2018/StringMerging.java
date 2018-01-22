package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;
import static net.egork.io.InputReader.readCharArray;

public class StringMerging {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[] a = in.readCharArray(n);
        char[] b = in.readCharArray(m);
        int[][] r1 = new int[n + 1][m + 1];
        int[][] r2 = new int[n + 1][m + 2];
        for (int i = 0; i <= n; i++) {
            for (int j = i == 0 ? 1 : 0; j <= m; j++) {
                r1[i][j] = n + m;
                if (i > 0) {
                    if (i > 1 && a[i - 1] == a[i - 2]) {
                        r1[i][j] = min(r1[i][j], r1[i - 1][j]);
                    } else {
                        r1[i][j] = min(r1[i][j], r1[i - 1][j] + 1);
                    }
                    if (j > 0 && a[i - 1] == b[j - 1]) {
                        r1[i][j] = min(r1[i][j], r2[i - 1][j]);
                    } else {
                        r1[i][j] = min(r1[i][j], r2[i - 1][j] + 1);
                    }
                }
                r2[i][j] = n + m;
                if (j > 0) {
                    if (j > 1 && b[j - 1] == b[j - 2]) {
                        r2[i][j] = min(r2[i][j], r2[i][j - 1]);
                    } else {
                        r2[i][j] = min(r2[i][j], r2[i][j - 1] + 1);
                    }
                    if (i > 0 && a[i - 1] == b[j - 1]) {
                        r2[i][j] = min(r2[i][j], r1[i][j - 1]);
                    } else {
                        r2[i][j] = min(r2[i][j], r1[i][j - 1] + 1);
                    }
                }
            }
        }
        out.printLine(min(r1[n][m], r2[n][m]));
    }
}
