package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        for (int i = 1; ; i++) {
            if (i * i >= n) {
                out.printLine(i);
                return;
            }
        }
    }
}
