package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        long k = in.readLong();
        if (k > n + 1) {
            k = 2 * n + 2 - k;
        }
        long answer = max(0, (k - 1) / 2);
        out.printLine(answer);
    }
}
