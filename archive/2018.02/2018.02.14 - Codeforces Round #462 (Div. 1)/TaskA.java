package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        decreaseByOne(a);
        int[][] q = new int[2][n + 1];
        for (int i = 0; i < n; i++) {
            q[0][i + 1] = q[0][i];
            q[1][i + 1] = q[1][i];
            q[a[i]][i + 1]++;
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int middle = 0;
            for (int j = i; j < n; j++) {
                if (a[j] == 0) {
                    middle++;
                } else {
                    middle = Math.max(middle, q[1][j + 1] - q[1][i]);
                }
                answer = Math.max(answer, middle + q[0][i] + q[1][n] - q[1][j + 1]);
            }
        }
        out.printLine(answer);
    }
}
