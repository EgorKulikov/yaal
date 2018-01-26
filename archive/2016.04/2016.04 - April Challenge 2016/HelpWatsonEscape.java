package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.MOD7;

public class HelpWatsonEscape {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        out.printLine(k * IntegerUtils.power(k - 1, n - 1, MOD7) % MOD7);
    }
}
