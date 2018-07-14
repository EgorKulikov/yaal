package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.numbers.IntegerUtils.gcd;

public class BRng10s {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.readLong();
        long b = in.readLong();
        long c = in.readLong();
        long d = in.readLong();
        if (d < b || a < b) {
            out.printLine("No");
            return;
        }
        if (c >= b - 1) {
            out.printLine("Yes");
            return;
        }
        a %= b;
        d = gcd(d, b);
        long max = b - d + a % d;
        if (max > c) {
            out.printLine("No");
        } else {
            out.printLine("Yes");
        }
    }
}
