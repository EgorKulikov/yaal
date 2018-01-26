package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long answer = 2 * 4 * 3 * IntegerUtils.power(4, n - 3);
        if (n >= 4) {
            answer += (n - 3) * 4 * 3 * 3 * IntegerUtils.power(4, n - 4);
        }
        out.printLine(answer);
    }
}
