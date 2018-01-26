package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int l = in.readInt();
        int v1 = in.readInt();
        int v2 = in.readInt();
        int k = in.readInt();
        int alpha = (n + k - 1) / k;
        double a0 = (double)v1 * (alpha - 1) + v2;
        double b0 = (double)v1 * (alpha - 1);
        double c0 = l;
        double a1 = v2 - v1;
        double b1 = -v2 - v1;
        double c1 = 0;
        double t2 = -(c0 * b1 - c1 * b0) / (b0 * a1 - b1 * a0);
        double t1 = (l - v2 * t2) / v1;
        out.printLine(t1 + t2);
    }
}
