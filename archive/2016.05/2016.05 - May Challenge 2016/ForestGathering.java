package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.io.IOUtils.readLongArrays;

public class ForestGathering {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long w = in.readLong();
        long l = in.readLong();
        long[] h = new long[n];
        long[] r = new long[n];
        readLongArrays(in, h, r);
        long left = 0;
        long right = max(w, l);
        while (left < right) {
            long middle = (left + right) >> 1;
            long remaining = w;
            for (int i = 0; i < n && remaining > 0; i++) {
                if ((max(remaining, l) - h[i]) / r[i] <= middle - 1) {
                    remaining = 0;
                    break;
                }
                long height = h[i] + r[i] * middle;
                if (height >= l) {
                    remaining -= height;
                }
            }
            if (remaining <= 0) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        out.printLine(left);
    }
}
