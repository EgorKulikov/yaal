package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MAX_VALUE;
import static net.egork.misc.ArrayUtils.minPosition;

public class ChangeTheSigns {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        long[][] result = new long[n][4];
        int[][] last = new int[n][4];
        for (int i = 0; i < 4; i++) {
            long current = 0;
            for (int j = 0; j < 2; j++) {
                if ((i >> j & 1) == 0) {
                    current += a[1 - j];
                } else {
                    current -= a[1 - j];
                }
            }
            if (current > 0) {
                result[1][i] = current;
            } else {
                result[1][i] = MAX_VALUE / 2;
            }
        }
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = MAX_VALUE / 2;
                long current = 0;
                for (int k = 0; k < 2; k++) {
                    if ((j >> k & 1) == 0) {
                        current += a[i - k];
                    } else {
                        current -= a[i - k];
                    }
                }
                if (current <= 0) {
                    continue;
                }
                for (int l = 0; l < 2; l++) {
                    int jj = l * 4 + j;
                    current = 0;
                    for (int k = 0; k < 3; k++) {
                        if ((jj >> k & 1) == 0) {
                            current += a[i - k];
                        } else {
                            current -= a[i - k];
                        }
                    }
                    if (current > 0) {
                        long candidate = result[i - 1][jj >> 1];
                        if ((j & 1) == 0) {
                            candidate += a[i];
                        } else {
                            candidate -= a[i];
                        }
                        if (result[i][j] > candidate) {
                            result[i][j] = candidate;
                            last[i][j] = jj >> 1;
                        }
                    }
                }
            }
        }
        int at = minPosition(result[n - 1]);
        for (int i = n - 1; i > 1; i--) {
            if ((at & 1) == 1) {
                a[i] = -a[i];
            }
            at = last[i][at];
        }
        if ((at & 1) == 1) {
            a[1] = -a[1];
        }
        if ((at & 2) == 2) {
            a[0] = -a[0];
        }
        out.printLine(a);
    }
}
