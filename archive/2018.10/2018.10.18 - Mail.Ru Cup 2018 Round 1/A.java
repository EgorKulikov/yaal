package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.abs;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.readInt();
        int y = in.readInt();
        int z = in.readInt();
        int t1 = in.readInt();
        int t2 = in.readInt();
        int t3 = in.readInt();
        int stairs = abs(x - y) * t1;
        int elevator = (abs(x - z) + abs(x - y)) * t2 + 3 * t3;
        out.printLine(elevator <= stairs ? "YES" : "NO");
    }
}
