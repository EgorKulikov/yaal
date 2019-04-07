package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.sort;

public class BAlenaIUzkiiHolodilnik {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int h = in.readInt();
        int[] a = in.readIntArray(n);
        for (int i = 1; i <= n; i++) {
            sort(a, 0, i);
            long total = 0;
            for (int j = i - 1; j >= 0; j -= 2) {
                total += a[j];
            }
            if (total > h) {
                out.printLine(i - 1);
                return;
            }
        }
        out.printLine(n);
    }
}
