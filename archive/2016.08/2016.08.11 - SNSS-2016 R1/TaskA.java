package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int r = in.readInt();
        double d = r / cos(PI / n);
        for (int i = 0; i < n; i++) {
            out.printLine(d * cos(2 * i * PI / n), d * sin(2 * i * PI / n));
        }
    }
}
