package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readInt();
        long m = in.readInt();
        if (m <= n && n * (n + 1) / 2 % 2 == m % 2) {
            out.printLine("YES");
        } else {
            out.printLine("NO");
        }
    }
}
