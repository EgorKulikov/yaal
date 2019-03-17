package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class APoisonousCookies {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        if (c <= a + b + 1) {
            out.printLine(b + c);
        } else {
            out.printLine(2 * b + a + 1);
        }
    }
}
