package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class EmmasNotebook {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.readInt();
        long t1 = (t + 1) / 2;
        long t2 = t / 2 + 1;
        out.printLine(t1 * (t1 + 1) / 2 + t2 * (t2 + 1) / 2 - 1);
    }
}
