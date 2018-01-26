package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long s = in.readLong();
        long x = in.readLong();
        long answer = 1;
        boolean canZero = true;
        boolean canOne = false;
        for (int i = 0; i < 40; i++) {
            if ((s >> i & 1) != (x >> i & 1)) {
                if (!canOne) {
                    out.printLine(0);
                    return;
                }
                canZero = false;
            } else if ((s >> i & 1) == (x >> i & 1)) {
                if (!canZero) {
                    out.printLine(0);
                    return;
                }
                canOne = false;
            }
            if ((x >> i & 1) == 1) {
                answer *= 2;
            } else {
                canOne = true;
                canZero = true;
            }
        }
        if (s == x) {
            answer -= 2;
        }
        out.printLine(answer);
    }
}
