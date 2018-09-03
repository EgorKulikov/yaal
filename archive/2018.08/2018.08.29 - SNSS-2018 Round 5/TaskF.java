package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String p = in.readString();
        String q = in.readString();
        if (p.equals(q)) {
            out.printLine(p);
        } else {
            out.printLine(1);
        }
    }
}
