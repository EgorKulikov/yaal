package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class AFairness {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.readLong();
        long b = in.readLong();
        in.readLong();
        long k = in.readLong();
        if (k % 2 == 0) {
            out.printLine(a - b);
        } else {
            out.printLine(b - a);
        }
    }
}
