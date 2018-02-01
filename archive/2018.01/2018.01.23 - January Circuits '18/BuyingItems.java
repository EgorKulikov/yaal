package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MAX_VALUE;
import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.count;
import static net.egork.misc.ArrayUtils.createArray;

public class BuyingItems {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        boolean[][] has = new boolean[m][n];
        int[] c = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                has[i][j] = in.readInt() == 1;
            }
            c[i] = in.readInt();
        }
        if (n <= m) {
            long[] cost = createArray(1 << n, MAX_VALUE / 2);
            cost[0] = 0;
            for (int i = 0; i < m; i++) {
                int mask = 0;
                for (int j = 0; j < n; j++) {
                    if (has[i][j]) {
                        mask += 1 << j;
                    }
                }
                for (int j = 0; j < (1 << n); j++) {
                    cost[j | mask] = Math.min(cost[j | mask], cost[j] + c[i]);
                }
            }
            out.printLine(cost[cost.length - 1]);
        } else {
            long answer = MAX_VALUE;
            boolean[] done = new boolean[n];
            for (int i = 0; i < (1 << m); i++) {
                fill(done, false);
                long price = 0;
                for (int j = 0; j < m; j++) {
                    if ((i >> j & 1) == 1) {
                        price += c[j];
                        for (int k = 0; k < n; k++) {
                            done[k] |= has[j][k];
                        }
                    }
                }
                if (count(done, false) == 0) {
                    answer = Math.min(answer, price);
                }
            }
            out.printLine(answer);
        }
    }
}
