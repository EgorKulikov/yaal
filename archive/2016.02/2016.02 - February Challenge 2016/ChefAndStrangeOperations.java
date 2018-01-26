package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class ChefAndStrangeOperations {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int x = in.readInt() - 1;
        long m = in.readLong();
        long[] a = readLongArray(in, n);
        long answer = 0;
        long multiplier = 1;
        for (int i = x; i >= 0; i--) {
            answer += a[i] % MOD * multiplier % MOD;
            multiplier *= IntegerUtils.reverse(x - i + 1, MOD);
            multiplier %= MOD;
            multiplier *= (x - i + m) % MOD;
            multiplier %= MOD;
        }
        out.printLine(answer % MOD);
    }
}
