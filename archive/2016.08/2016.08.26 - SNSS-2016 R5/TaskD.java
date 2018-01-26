package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.io.IOUtils.readIntTable;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] k = readIntArray(in, m);
        int[][] a = readIntTable(in, m, n);
        long[] answer = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i + 1];
            for (int j = 0; j < m; j++) {
                if (i + k[j] <= n) {
                    answer[i] = Math.max(answer[i], answer[i + k[j]] + a[j][i]);
                }
            }
        }
        out.printLine(answer[0]);
    }
}
