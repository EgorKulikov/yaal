package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.io.IOUtils.readIntArray;

public class TraderProfit {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int[][] answer = new int[k + 1][n + 1];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = j + 1; l < n; l++) {
                    answer[i + 1][l + 1] = max(answer[i + 1][l + 1], answer[i][j] + max(0, a[l] - a[j]));
                }
            }
            for (int j = 1; j <= n; j++) {
                answer[i + 1][j] = Math.max(answer[i + 1][j], answer[i + 1][j - 1]);
            }
        }
        int result = 0;
        for (int i = 0; i <= n; i++) {
            result = Math.max(result, answer[k][i]);
        }
        out.printLine(result);
    }
}
