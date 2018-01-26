package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TheEduardosGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long count = in.readLong();
        out.printLine(IntegerUtils.power(2, count, (long) (1e9 + 7)) - 1);
    }
}
