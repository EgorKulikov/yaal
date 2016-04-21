package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int s = in.readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.readInt();
        }
        int nn = (1 << n);
        int[] pl = new int[nn];
        int[] ls = new int[nn];
        int[] p = new int[nn];
        pl[0] = 1;

        for (int m = 1; m < nn; m++) {
            pl[m] = n + 1;
            for (int i = 0; i < n; i++) {
                if ((m & (1 << i)) != 0) {
                    int mm = m - (1 << i);
                    int ppl = pl[mm];
                    int lls = ls[mm];
                    lls += a[i];
                    if (lls > s) {
                        ppl++;
                        lls = a[i];
                    }
                    if (ppl < pl[m] || ppl == pl[m] && lls < ls[m]) {
                        pl[m] = ppl;
                        ls[m] = lls;
                        p[m] = i;
                    }
                }
            }
        }
        out.printLine(pl[nn - 1]);
        int c = nn - 1;
        int[] q = new int[n];
        while (c > 0) {
            int k = 0;
            int ss = 0;
            while (c > 0 && ss + a[p[c]] <= s) {
                ss += a[p[c]];
                q[k++] = p[c];
                c -= (1 << p[c]);
            }
            out.print(k);
            for (int i = 0; i < k; i++) {
                out.print(" " + (q[i] + 1));
            }
            out.printLine();
        }
    }
}
