package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class ChefAndMagicalPath {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        long m = in.readLong();
        if ((n % 2 == 0 || m % 2 == 0) && (n >= 2 && m >= 2) || min(n, m) == 1 && max(n, m) == 2) {
            out.printLine("Yes");
        } else {
            out.printLine("No");
        }
    }
}
