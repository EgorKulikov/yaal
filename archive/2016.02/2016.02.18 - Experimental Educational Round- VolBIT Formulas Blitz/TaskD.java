package net.egork;

import net.egork.numbers.Interpolation;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        out.printLine(3 * n * n + 3 * n + 1);
    }
}
