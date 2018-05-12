package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;
import static java.util.Arrays.sort;
import static net.egork.misc.ArrayUtils.reverse;

public class BitParty {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        int[] m = new int[c];
        int[] s = new int[c];
        int[] p = new int[c];
        in.readIntArrays(m, s, p);
        long[] qty = new long[c];
        long left = 0;
        long right = 2_000_000_000_000_000_000L;
        while (left < right) {
            long middle = (left + right) >> 1;
            for (int i = 0; i < c; i++) {
                if (p[i] > middle) {
                    qty[i] = 0;
                } else {
                    qty[i] = min(m[i], (middle - p[i]) / s[i]);
                }
            }
            sort(qty);
            reverse(qty);
            long current = 0;
            for (int i = 0; i < r; i++) {
                current += qty[i];
            }
            if (current >= b) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        out.printLine("Case #" + testNumber + ":", left);
    }
}
