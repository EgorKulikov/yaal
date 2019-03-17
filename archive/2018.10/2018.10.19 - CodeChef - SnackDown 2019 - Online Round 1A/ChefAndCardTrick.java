package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndCardTrick {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int changes = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) {
                changes++;
            }
        }
        if (changes > 1 || changes == 1 && a[0] < a[n - 1]) {
            out.printLine("NO");
        } else {
            out.printLine("YES");
        }
    }
}
