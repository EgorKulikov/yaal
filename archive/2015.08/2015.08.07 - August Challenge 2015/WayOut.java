package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class WayOut {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int ht = in.readInt();
        int[] l = new int[n];
        int[] h = new int[n];
        IOUtils.readIntArrays(in, l, h);
        long[] delta = new long[n];
        delta[0] = n;
        for (int i = 0; i < n; i++) {
            delta[l[i]]--;
            if (h[i] != n - 1) {
                delta[h[i] + 1]++;
            }
        }
        long[] qty = new long[n];
        qty[0] = delta[0];
        for (int i = 1; i < n; i++) {
            qty[i] = delta[i] + qty[i - 1];
        }
        long current = 0;
        for (int i = 0; i < ht - 1; i++) {
            current += qty[i];
        }
        long answer = Long.MAX_VALUE;
        for (int i = ht - 1; i < n; i++) {
            current += qty[i];
            answer = Math.min(answer, current);
            current -= qty[i - ht + 1];
        }
        out.printLine(answer);
    }
}
