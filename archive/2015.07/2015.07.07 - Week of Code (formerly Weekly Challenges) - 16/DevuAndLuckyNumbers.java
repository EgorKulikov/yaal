package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DevuAndLuckyNumbers {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n4 = in.readInt();
        int n5 = in.readInt();
        int n6 = in.readInt();
        long[][][] answer = new long[n4 + 1][n5 + 1][n6 + 1];
        long[][][] qty = new long[n4 + 1][n5 + 1][n6 + 1];
        qty[0][0][0] = 1;
        long total = 0;
        for (int i = 0; i <= n4; i++) {
            for (int j = 0; j <= n5; j++) {
                for (int k = 0; k <= n6; k++) {
                    if (i > 0) {
                        answer[i][j][k] += answer[i - 1][j][k] * 10 + qty[i - 1][j][k] * 4;
                        qty[i][j][k] += qty[i - 1][j][k];
                    }
                    if (j > 0) {
                        answer[i][j][k] += answer[i][j - 1][k] * 10 + qty[i][j - 1][k] * 5;
                        qty[i][j][k] += qty[i][j - 1][k];
                    }
                    if (k > 0) {
                        answer[i][j][k] += answer[i][j][k - 1] * 10 + qty[i][j][k - 1] * 6;
                        qty[i][j][k] += qty[i][j][k - 1];
                    }
                    answer[i][j][k] %= MOD;
                    total += answer[i][j][k];
                    qty[i][j][k] %= MOD;
                }
            }
        }
        out.printLine(total % MOD);
    }
}
