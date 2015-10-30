package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] x = new int[m];
        int[] y = new int[m];
        int[] a = new int[m];
        int[] a0 = new int[m];
        int[] a1 = new int[m];
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            x[i] = in.readInt() - 1;
            y[i] = in.readInt() - 1;
            a[i] = x[i] + y[i];
            b[i] = x[i] - y[i];
        }
        Arrays.sort(a);
        int k0 = 0;
        int k1 = 0;
        long res = 0;
        for (int i = 0; i < m; i++) {
            if (i == 0 || a[i] > a[i - 1]) {
                res += a[i] < n ? a[i] + 1 : 2 * n - 1 - a[i];
                if (a[i] % 2 == 0) {
                    a0[k0++] = a[i];
                } else {
                    a1[k1++] = a[i];
                }
            }
        }
        Arrays.sort(b);
        for (int i = 0; i < m; i++) {
            if (i == 0 || b[i] > b[i - 1]) {
                int l, r;
                if (b[i] > 0) {
                    l = b[i];
                    r = 2 * n - 2 - b[i];
                } else {
                    l = -b[i];
                    r = 2 * n - 2 + b[i];
                }
                res += r / 2 - l / 2 + 1;
                int k;
                if (l % 2 == 0) {
                    a = a0;
                    k = k0;
                } else {
                    a = a1;
                    k = k1;
                }
                int ll = -1;
                int rr = k;
                while (rr > ll + 1) {
                    int mm = (ll + rr) >> 1;
                    if (a[mm] < l) {
                        ll = mm;
                    } else {
                        rr = mm;
                    }
                }
                int q = rr;
                ll = -1;
                rr = k;
                while (rr > ll + 1) {
                    int mm = (ll + rr) >> 1;
                    if (a[mm] <= r) {
                        ll = mm;
                    } else {
                        rr = mm;
                    }
                }
                int w = ll;
                res -= (w - q + 1);
            }
        }
        out.printLine(1L * n * n - res);
    }
}
