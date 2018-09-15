package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class DMOPC18Contest1P1Sorting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int wildcard = 0;
        boolean found = false;
        int last = 1;
        for (int i : a) {
            if (i == 0) {
                if (wildcard == 0) {
                    found = true;
                    continue;
                }
                i = wildcard;
            } else if (found && wildcard == 0) {
                wildcard = i;
            }
            if (i < last) {
                out.printLine("NO");
                return;
            }
            last = i;
        }
        out.printLine("YES");
    }
}
