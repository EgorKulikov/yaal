package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (in.isExhausted()) {
            throw new UnknownError();
        }
        long k = in.readLong();
        long p = in.readLong();
        if (p == 2) {
            if (k % 2 == 1) {
                out.printLine("YES");
            } else {
                out.printLine("NO");
            }
            return;
        }
        if (k % (2 * (p - 1)) >= p - 1) {
            out.printLine("YES");
        } else {
            out.printLine("NO");
        }
    }
}
