package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] c = readIntArray(in, n);
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + c[i];
        }
        boolean[][] can = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            can[i][i] = true;
            for (int j = i; j < n; j++) {
                int a = i;
                int b = j;
                int d1 = c[i];
                int d2 = c[j];
                while (a < b) {
                    if (d1 < d2) {
                        d1 += c[++a];
                    } else if (d1 > d2) {
                        d2 += c[--b];
                    } else {
                        if (can[i][a] && can[b][j] && (a + 1 == b || can[a + 1][b - 1])) {
                            can[i][j] = true;
                            break;
                        }
                        d1 += c[++a];
                        d2 += c[--b];
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (can[j][i]) {
                    answer = Math.max(answer, sums[i + 1] - sums[j]);
                }
            }
        }
        out.printLine(answer);
    }
}
