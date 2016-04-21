package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskM {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long x = 1;
        long y = 0;
        for (int i = 0; i < n; i++) {
            int a = in.readInt();
            int b = in.readInt();
            x = IntegerUtils.lcm(x, a);
            y = IntegerUtils.gcd(y, b);
        }
        out.printLine(x, y);
    }
}
