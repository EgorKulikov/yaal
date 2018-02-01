package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class P1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.printLine("guess 50");
        out.flush();
        int delta = in.readInt();
        out.printLine("answer", 50 - delta);
    }
}
