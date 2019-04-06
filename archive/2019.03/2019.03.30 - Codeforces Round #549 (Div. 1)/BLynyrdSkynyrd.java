package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.createOrder;
import static net.egork.misc.ArrayUtils.reversePermutation;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class BLynyrdSkynyrd {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int q = in.readInt();
        int[] p = in.readIntArray(n);
        int[] a = in.readIntArray(m);
        decreaseByOne(p, a);
        int[] rev = reversePermutation(p);
        int[] next = createArray(n, m);
        int[] where = new int[m + 1];
        where[m] = m;
        for (int i = m - 1; i >= 0; i--) {
            int pos = rev[a[i]];
            if (pos == n - 1) {
                pos = 0;
            } else {
                pos++;
            }
            where[i] = next[p[pos]];
            next[a[i]] = i;
        }
        int[] ends = createOrder(m);
        for (int i = 0; (1 << i) < n; i++) {
            if (((n - 1) >> i & 1) == 1) {
                for (int j = 0; j < m; j++) {
                    ends[j] = where[ends[j]];
                }
            }
            for (int j = 0; j <= m; j++) {
                where[j] = where[where[j]];
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            ends[i] = Math.min(ends[i], ends[i + 1]);
        }
        StringBuilder answer = new StringBuilder(q);
        for (int i = 0; i < q; i++) {
            int l = in.readInt() - 1;
            int r = in.readInt() - 1;
            answer.append(ends[l] <= r ? 1 : 0);
        }
        out.printLine(answer);
    }
}
