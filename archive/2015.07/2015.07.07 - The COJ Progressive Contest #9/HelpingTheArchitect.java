package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class HelpingTheArchitect {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int g = IntegerUtils.gcd(a, b);
        out.printLine((2 * a + 2 * b) / g);
    }
}
