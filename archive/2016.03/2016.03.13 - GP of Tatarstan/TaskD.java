package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long l = in.readLong();
        long n = in.readLong();
        long k = in.readLong();
        if (k == 1) {
            out.printLine(0);
            return;
        }
        while (l > 0 && n > 0) {
            if (n % k == 1) n--;
            if (n == 0) break;
            long t = (n + k - 1) / k;
            long s = 2 * t - 1;
            long x = (n - 1 - (t - 1) * k + s - 1) / s;
            if (x > l) x = l;
            n -= s * x;
            l -= x;
        }
        if (n < 0) n = 0;
        out.printLine(n);
    }
}
