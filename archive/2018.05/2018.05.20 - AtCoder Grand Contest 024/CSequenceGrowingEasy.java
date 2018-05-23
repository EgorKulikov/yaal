package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class CSequenceGrowingEasy {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        if (a[0] != 0) {
            out.printLine(-1);
            return;
        }
        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1] + 1) {
                out.printLine(-1);
                return;
            }
        }
        long answer = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] == a[i - 1] + 1) {
                answer++;
            } else {
                answer += a[i];
            }
        }
        out.printLine(answer);
    }
}
