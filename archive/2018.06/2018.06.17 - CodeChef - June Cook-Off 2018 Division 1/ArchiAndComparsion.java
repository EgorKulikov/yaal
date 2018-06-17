package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.abs;

public class ArchiAndComparsion {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int n = in.readInt();
        if (n % 2 == 0) {
            a = abs(a);
            b = abs(b);
        }
        if (a > b) {
            out.printLine(1);
        } else if (a < b) {
            out.printLine(2);
        } else {
            out.printLine(0);
        }
    }
}
