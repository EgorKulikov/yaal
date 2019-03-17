package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class BMex {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > max) {
                out.printLine(i + 1);
                return;
            }
            max = Math.max(max, a[i] + 1);
        }
        out.printLine(-1);
    }
}
