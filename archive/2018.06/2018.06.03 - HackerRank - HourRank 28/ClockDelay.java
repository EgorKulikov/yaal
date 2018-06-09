package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ClockDelay {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int h1 = in.readInt();
        int m1 = in.readInt();
        int h2 = in.readInt();
        int m2 = in.readInt();
        int k = in.readInt();
        int delta = 60 * (h2 - h1) + m2 - m1;
        out.printLine(60 * k - delta);
    }
}
