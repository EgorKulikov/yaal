package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class GracefulChainsawJugglers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.readInt();
        int b = in.readInt();
        int[] max = new int[b + 1];
        for (int i = 0; i <= b; i++) {
            int rem = i;
            for (int j = 0; ; j++) {
                if (j > rem) {
                    break;
                }
                max[i]++;
                rem -= j;
            }
        }
        int[][][] answer = new int[50][r + 1][b + 1];
        for (int i = 0; i <= 48; i++) {
            for (int j = 0; j <= r; j++) {
                for (int k = 0; k <= b; k++) {
                    answer[i + 1][j][k] = Math.max(answer[i + 1][j][k], answer[i][j][k]);
                    int jj = j;
                    int kk = k;
                    for (int l = 0; ; l++) {
                        jj -= i;
                        kk -= l;
                        if (jj < 0 || kk < 0) {
                            break;
                        }
                        answer[i + 1][jj][kk] = Math.max(answer[i + 1][jj][kk], answer[i][j][k] + l + 1);
                    }
                }
            }
        }
        out.printLine("Case #" + testNumber + ":", answer[49][0][0] - 1);
    }
}
