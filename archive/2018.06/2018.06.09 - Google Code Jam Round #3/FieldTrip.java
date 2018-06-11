package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.misc.ArrayUtils.maxElement;
import static net.egork.misc.ArrayUtils.minElement;

public class FieldTrip {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] r = new int[n];
        int[] c = new int[n];
        in.readIntArrays(r, c);
        int delta = max(maxElement(r) - minElement(r), maxElement(c) - minElement(c));
        out.printLine("Case #" + testNumber + ":", (delta + 1) / 2);
    }
}
