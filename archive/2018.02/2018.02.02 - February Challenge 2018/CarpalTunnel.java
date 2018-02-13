package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;

public class CarpalTunnel {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int c = in.readInt();
        in.readInt();
        in.readInt();
        out.printLine((long)maxElement(a) * (c - 1));
    }
}
