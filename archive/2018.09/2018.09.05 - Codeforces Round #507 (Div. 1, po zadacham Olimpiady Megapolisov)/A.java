package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.decreaseByOne;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long t = in.readLong();
        long[] a = in.readLongArray(n);
        int[] x = in.readIntArray(n);
        decreaseByOne(x);
        for (int i = 0; i < n; i++) {
            if (x[i] < i || i > 0 && x[i] < x[i - 1] || i > 0 && x[i] != x[i - 1] && x[i - 1] != i - 1) {
                out.printLine("No");
                return;
            }
        }
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            if (x[i] == i) {
                if (i == 0 || x[i] != x[i - 1]) {
                    b[i] = a[i] + t;
                } else {
                    b[i] = b[i - 1] + 1;
                }
            } else {
                b[i] = a[i + 1] + t;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != 0 && b[i] <= b[i - 1]) {
                out.printLine("No");
                return;
            }
            if (x[i] < n - 1 && a[x[i] + 1] + t <= b[x[i]]) {
                out.printLine("No");
                return;
            }
        }
        out.printLine("Yes");
        out.printLine(b);
    }
}
