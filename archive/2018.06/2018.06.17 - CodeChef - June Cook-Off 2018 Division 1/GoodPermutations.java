package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.decreaseByOne;

public class GoodPermutations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        decreaseByOne(a);
        int[][][][] answer = new int[n + 1][n][k + 1][1 << n];
        answer[0][n - 1][k][(1 << n) - 1] = 1;
        for (int i = 0; i < n; i++) {
            int from = 0;
            int to = n - 1;
            if (a[i] != -1) {
                from = to = a[i];
            }
            for (int j = from; j <= to; j++) {
                for (int l = 0; l < n; l++) {
                    for (int m = 0; m <= k; m++) {
                        for (int o = 0; o < (1 << n); o++) {
                            if ((o >> j & 1) == 0) {
                                continue;
                            }
                            if (m == 0 && j > l) {
                                continue;
                            }
                            int nm = j > l ? m - 1 : m;
                            answer[i + 1][j][nm][o - (1 << j)] += answer[i][l][m][o];
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += answer[n][i][0][0];
        }
        out.printLine(result);
    }
}
