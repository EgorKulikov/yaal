package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskN {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double a = in.readInt();
        double b = in.readInt();
        double c = in.readInt();
        double d = Math.sqrt(b * b - 4 * a * c);
        if (a > 0) {
            out.printLine((-b + d) / (2 * a));
            out.printLine((-b - d) / (2 * a));
        } else {
            out.printLine((-b - d) / (2 * a));
            out.printLine((-b + d) / (2 * a));
        }
    }
}
