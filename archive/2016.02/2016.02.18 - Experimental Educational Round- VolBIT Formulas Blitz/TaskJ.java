package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        long x = 1;
        for (int i = 2; i <= 10; i++) {
            x = IntegerUtils.lcm(i, x);
        }
        out.printLine(n / x);
    }
}
