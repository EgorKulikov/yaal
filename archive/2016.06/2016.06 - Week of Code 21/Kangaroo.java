package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Kangaroo {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x1 = in.readInt();
        int v1 = in.readInt();
        int x2 = in.readInt();
        int v2 = in.readInt();
        if (v1 <= v2) {
            out.printLine("NO");
            return;
        }
        out.printLine((x2 - x1) % (v1 - v2) == 0 ? "YES" : "NO");
    }
}
