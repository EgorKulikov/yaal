package net.egork;

import net.egork.numbers.IntegerUtils;

import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.sort;
import static net.egork.misc.ArrayUtils.unique;

public class IncreasingSequences {
    final static int MOD = 998244353;

    public int count(int[] L, int[] R) {
        int n = L.length;
        int[] all = new int[2 * n + 1];
        for (int i = 0; i < n; i++) {
            all[2 * i] = L[i];
            all[2 * i + 1] = ++R[i];
        }
        sort(all);
        all = unique(all);
        long[][] ways = new long[n + 1][all.length - 1];
        long[][] total = new long[n + 1][all.length - 1];
        ways[0][0] = 1;
        fill(total[0], 1);
        long[] reverse = IntegerUtils.generateReverse(n + 10, MOD);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < all.length - 1; j++) {
                if (L[i] <= all[j] && all[j + 1] <= R[i]) {
                    long vars = 1;
                    int mulBy = all[j + 1] - all[j];
                    int divBy = 1;
                    for (int k = i; k >= 0; k--) {
                        vars *= mulBy;
                        vars %= MOD;
                        vars *= reverse[divBy];
                        vars %= MOD;
                        mulBy--;
                        divBy++;
                        if (vars == 0) {
                            break;
                        }
                        ways[i + 1][j] += total[k][j - 1] * vars % MOD;
                        if (k > 0 && (L[k - 1] > all[j] || R[k - 1] < all[j + 1])) {
                            break;
                        }
                    }
                }
                ways[i + 1][j] %= MOD;
                total[i + 1][j] = (total[i + 1][j - 1] + ways[i + 1][j]) % MOD;
            }
        }
        return (int) total[n][all.length - 2];
    }
}
