package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class CColoringTorus {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        int n = Math.min(k, 500);
        int[][] temp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            if (i + 1 < k) {
                for (int j = 0; j < n; j += 2) {
                    temp[i][j] = k;
                }
                k--;
                for (int j = 1; j < n; j += 2) {
                    temp[i][j] = k;
                }
                k--;
            } else {
                for (int j = 0; j < n; j++) {
                    temp[i][j] = k;
                }
                k--;
            }
        }
        int[][] answer = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer[j][(i + j) % n] = temp[i][j];
            }
        }
        out.printLine(n);
        for (int[] row : answer) {
            out.printLine(row);
        }
    }
}
