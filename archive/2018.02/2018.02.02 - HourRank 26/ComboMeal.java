package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ComboMeal {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int b = in.readInt();
        int s = in.readInt();
        int c = in.readInt();
        out.printLine(b + s - c);
    }
}
