package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class NASAVisit {
    static final long MOD = (long) (1e9 + 7);

    long[] alpha = new long[100002];
    long[] beta = new long[100002];

    {
        alpha[0] = alpha[1] = 1;
        beta[2] = 1;
        for (int i = 3; i < alpha.length; i++) {
            alpha[i] = (alpha[i - 1] + alpha[i - 2] + alpha[i - 3]) % MOD;
            beta[i] = (beta[i - 1] + beta[i - 2] + beta[i - 3]) % MOD;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int ti = in.readInt();
        out.printLine(alpha[ti + 1], beta[ti + 1]);
    }
}
