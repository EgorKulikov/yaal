package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class OneMoreWeirdGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        out.printLine((n - 1) * m + (m - 1) * n);
    }
}
