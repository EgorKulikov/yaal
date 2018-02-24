package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.System.arraycopy;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[][] seq = in.readIntTable(2 * n, n);
        int firstColumn = -1;
        for (int i = 0; i < 2 * n && firstColumn == -1; i++) {
            for (int j = 0; j < i; j++) {
                if (seq[i][0] == seq[j][0]) {
                    firstColumn = i;
                    break;
                }
            }
        }
        int[] answer = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                if (seq[firstColumn][i] == seq[j][0]) {
                    arraycopy(seq[j], 0, answer, i * n, n);
                    break;
                }
            }
        }
        out.printLine(answer);
    }
}
