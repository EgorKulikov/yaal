package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x1 = new int[n];
        int[] y1 = new int[n];
        int[] x2 = new int[n];
        int[] y2 = new int[n];
        in.readIntArrays(x1, y1, x2, y2);
        int[] mx1 = new int[n + 1];
        int[] my1 = new int[n + 1];
        int[] mx2 = new int[n + 1];
        int[] my2 = new int[n + 1];
        mx1[n] = my1[n] = MIN_VALUE;
        mx2[n] = my2[n] = MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            mx1[i] = max(mx1[i + 1], x1[i]);
            my1[i] = max(my1[i + 1], y1[i]);
            mx2[i] = min(mx2[i + 1], x2[i]);
            my2[i] = min(my2[i + 1], y2[i]);
        }
        int cx1 = MIN_VALUE;
        int cy1 = MIN_VALUE;
        int cx2 = MAX_VALUE;
        int cy2 = MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (max(cx1, mx1[i + 1]) <= min(cx2, mx2[i + 1]) && max(cy1, my1[i + 1]) <= min(cy2, my2[i + 1])) {
                out.printLine(max(cx1, mx1[i + 1]), max(cy1, my1[i + 1]));
                return;
            }
            cx1 = Math.max(cx1, x1[i]);
            cy1 = Math.max(cy1, y1[i]);
            cx2 = Math.min(cx2, x2[i]);
            cy2 = Math.min(cy2, y2[i]);
        }
        while (true);
    }
}
