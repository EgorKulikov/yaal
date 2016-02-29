package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class EMusicalSequences {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        long z = in.readLong();
        int[] a = readIntArray(in, n);
        z %= 2 * (n + 1);
        if ((n + 1) % 2 == 0) {
            z %= n + 1;
        }
        long next = 0;
        for (int i : a) {
            next *= -1;
            next += i;
        }
        next %= m;
        if (next < 0) {
            next += m;
        }
        if (z < n + 1) {
            if (z < n) {
                out.printLine(a[(int) z]);
            } else {
                out.printLine(next);
            }
        } else {
            z -= n + 1;
            int value = (int) (m - (z < n ? a[(int) z] : next));
            if (value == m) {
                value = 0;
            }
            out.printLine(value);
        }
    }
}
