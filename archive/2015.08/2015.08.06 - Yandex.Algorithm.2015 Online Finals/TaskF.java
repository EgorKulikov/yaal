package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = IOUtils.readIntArray(in, 2 * n);
        int x = 100000000;
        long sBase = (a[0] * (1L << 32)) / x;
        long mod = (1L << 32) - 1;
        for (long s = sBase; s < sBase + 50; s++) {
            boolean good = true;
            long cur = s & mod;
            for (int i = 0; i < 2 * n; i++) {
                if ((cur * x >> 32) != a[i]) {
                    good = false;
                    break;
                }
                cur = (cur * 134775813 + 1) & mod;
            }
            if (good) {
                out.printLine("RAW");
                return;
            }
        }
        out.printLine("SHUFFLED");
    }
}
