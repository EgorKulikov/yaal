package net.egork;

import net.egork.generated.collections.set.LongHashSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.DX4;
import static net.egork.misc.MiscUtils.DY4;
import static net.egork.misc.MiscUtils.isValidCell;
import static net.egork.numbers.IntegerUtils.generatePrimes;
import static net.egork.numbers.IntegerUtils.lcm;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        LongHashSet set = new LongHashSet();
        int[] p = generatePrimes(100000);
        long[][] a = new long[n][n];
        for (int i = 0; i <= 2 * n - 2; i += 2) {
            for (int j = (n - 1) & 1; j <= 2 * n - 2; j += 2) {
                int x = (i + j - (n - 1)) / 2;
                int y = i - x;
                if (isValidCell(x, y, n, n)) {
                    a[x][y] = p[i / 2] * p[n + j / 2];
                    set.add(a[x][y]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = (1 - (i & 1)); j < n; j += 2) {
                long lcm = 1;
                for (int k = 0; k < 4; k++) {
                    int ni = i + DX4[k];
                    int nj = j + DY4[k];
                    if (isValidCell(ni, nj, n, n)) {
                        lcm = lcm(lcm, a[ni][nj]);
                    }
                }
                for (long k = lcm + 1; ; k += lcm) {
                    if (!set.contains(k)) {
                        set.add(k);
                        a[i][j] = k;
                        if (k > 1000000000000000L) {
                            throw new RuntimeException();
                        }
                        break;
                    }
                }
            }
        }
        for (long[] row : a) {
            out.printLine(row);
        }
    }
}
