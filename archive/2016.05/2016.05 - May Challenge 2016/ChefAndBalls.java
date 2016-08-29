package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndBalls {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        in = new InputReader(System.in);
        out = new OutputWriter(System.out);
        out.printLine("1");
        out.printLine("3 1 1 2");
        out.printLine("3 4 5 5");
        out.flush();
        out.printLine(2);
        out.printLine(3 - in.readInt());
        out.flush();
    }
}
