package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.util.Arrays.fill;
import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.MiscUtils.MOD7;

public class ChefAndBigSoccer {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int s = in.readInt() - 1;
        int[] a = readIntArray(in, m);
        int[] current = new int[n];
        current[s] = 1;
        int[] next = new int[n];
        for (int i = 0; i < m; i++) {
            fill(next, 0);
            for (int j = 0; j < n; j++) {
                if (j >= a[i]) {
                    next[j - a[i]] += current[j];
                }
                if (j + a[i] < n) {
                    next[j + a[i]] += current[j];
                }
            }
            for (int j = 0; j < n; j++) {
                if (next[j] >= MOD7) {
                    current[j] = next[j] - MOD7;
                } else {
                    current[j] = next[j];
                }
            }
        }
        out.printLine(current);
    }
}
