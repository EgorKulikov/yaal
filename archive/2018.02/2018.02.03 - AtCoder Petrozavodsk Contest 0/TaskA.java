package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.readInt();
        int y = in.readInt();
        if (x % y == 0) {
            out.printLine(-1);
        } else {
            out.printLine(x);
        }
    }
}
