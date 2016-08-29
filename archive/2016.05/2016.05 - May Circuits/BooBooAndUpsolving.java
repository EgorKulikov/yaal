package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readLongArray;

public class BooBooAndUpsolving {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        long[] q = readLongArray(in, n);
        long left = 0;
        long right = 0;
        for (long i : q) {
            left = Math.max(left, i);
            right += i;
        }
        while (left < right) {
            long middle = (left + right) >> 1;
            int days = m - 1;
            long current = 0;
            for (long i : q) {
                if (current + i > middle) {
                    days--;
                    if (days < 0) {
                        break;
                    }
                    current = 0;
                }
                current += i;
            }
            if (days < 0) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        out.printLine(left);
    }
}
