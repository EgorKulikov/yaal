package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] state = in.readCharArray(n);
        out.printLine(state[n - 1]);
    }
}
