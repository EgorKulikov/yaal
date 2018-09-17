package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.reverse;

public class ProblemCErrorSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        int n = in.readInt();
        int m = in.readInt();
        int[] a = in.readIntArray(k);
        int[] b = in.readIntArray(m);
        int last = b[m - 1];
        for (int i = 0; i < m; i++) {
            b[i] = last - b[i];
        }
        reverse(b);
        last = a[k - 1];
        for (int i = 0; i < k; i++) {
            a[i] = last - a[i];
        }
        reverse(a);
//        IntHashMap id = new IntHashMap();
//        for (int i = 0; i < k; i++) {
//            id.put(a[i], i);
//        }
        int[] has = new int[(a[k - 1] + 31) >> 5];
        for (int i = 0; i < k; i++) {
            has[a[i] >> 5] |= 1 << (a[i] & 31);
        }
        int answer = -1;
        int[] next = new int[m + 1];
        int[] prev = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            next[i] = i + 1;
            prev[i] = i - 1;
        }
        for (int i = k - m; i >= 0; i--) {
            int upTo = min(i + n - 1, k - 1);
            boolean ok = true;
            if (a[upTo] - a[i] < b[m - 1]) {
                continue;
            }
            for (int j = next[0]; j < m; j = next[j]) {
                int current = a[i] + b[j];
                if ((has[current >> 5] >> (current & 31) & 1) == 0) {
                    ok = false;
                    int p = prev[j];
                    next[p] = next[j];
                    prev[next[j]] = p;
                    int q = next[0];
                    next[j] = q;
                    prev[q] = j;
                    next[0] = j;
                    prev[j] = 0;
                    break;
                }
            }
            if (ok) {
                answer = upTo;
                break;
            }
        }
        out.printLine(last - a[answer]);
    }
}
