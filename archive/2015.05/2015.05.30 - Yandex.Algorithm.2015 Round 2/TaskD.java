package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long number = in.readLong();
        int p2 = 0;
        while (number % 2 == 0) {
            number /= 2;
            p2++;
        }
        int p5 = 0;
        while (number % 5 == 0) {
            number /= 5;
            p5++;
        }
        if (number != 1) {
            out.printLine("impossible");
        } else {
            out.printLine(Math.max(p2, p5));
        }
    }
}
