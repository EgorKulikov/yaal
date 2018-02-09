package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(n);
        long needA = 0;
        long needB = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > b[i]) {
                needB += a[i] - b[i];
            } else {
                int x = (b[i] - a[i] + 1) / 2;
                needA += x;
                a[i] += 2 * x;
                needB += a[i] - b[i];
            }
        }
        if (needA >= needB) {
            out.printLine("Yes");
        } else {
            out.printLine("No");
        }
    }
}
