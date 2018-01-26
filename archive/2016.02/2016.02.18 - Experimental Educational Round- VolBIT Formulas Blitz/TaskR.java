package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskR {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        out.printLine(n % 2 == 0 ? 2 : 1);
    }
}
