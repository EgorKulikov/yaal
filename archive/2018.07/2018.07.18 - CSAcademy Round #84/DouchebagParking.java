package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class DouchebagParking {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int w = in.readInt();
        int[] free = new int[n];
        int[] l = new int[n];
        in.readIntArrays(free, l);
        int start = -1;
        int available = 0;
        for (int i = 0; i <= n; i++) {
            if (i != n && free[i] == 1) {
                if (start == -1) {
                    start = i;
                }
                available += l[i];
            } else {
                if (available >= w) {
                    out.printLine(start + 1);
                    return;
                }
                start = -1;
                available = 0;
            }
        }
        out.printLine(-1);
    }
}
