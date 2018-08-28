package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 1500; i++) {
            a.append(4);
            b.append(5);
        }
        a.append(5);
        b.append(5);
        out.printLine(a);
        out.printLine(b);
    }
}
