package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskM {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long x = -in.readLong();
        x %= 360;
        if (x < 0) {
            x += 360;
        }
        long best = 0;
        long angle = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            long candidate = min(x, 360 - x);
            if (candidate < angle) {
                angle = candidate;
                best = i;
            }
            x += 90;
            x %= 360;
        }
        out.printLine(best);
    }
}
