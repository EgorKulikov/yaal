package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class MikeAndCongruenceRelation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readInt();
        long k = in.readInt();
        long qty = n / k;
        out.printLine((n % k) * qty * (qty + 1) / 2 + (k - n % k) * qty * (qty - 1) / 2);
    }
}
