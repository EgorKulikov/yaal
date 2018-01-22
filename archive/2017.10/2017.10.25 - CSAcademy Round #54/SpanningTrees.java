package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class SpanningTrees {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        if (k == 0) {
            if (n == 2 || n == 3) {
                out.printLine(-1);
                return;
            }
            if (n == 1) {
                out.printLine(0);
                return;
            }
            out.printLine(2 * n - 2);
            for (int i = 0; i < n - 1; i++) {
                out.printLine(i + 1, i + 2, 1);
            }
            if (n % 2 == 0) {
                out.printLine(1, n / 2 + 1, 2);
            }
            int start = 1;
            int middle = n / 2 + 2;
            while (middle <= n) {
                out.printLine(start, middle, 2);
                start++;
                out.printLine(start, middle, 2);
                middle++;
            }
            return;
        }
        out.printLine(2 * n - 2 - k);
        for (int i = 0; i < k; i++) {
            out.printLine(i + 1, i + 2, 2);
        }
        for (int i = k + 2; i <= n; i++) {
            out.printLine(1, i, 1);
            out.printLine(2, i, 3);
        }
    }
}
