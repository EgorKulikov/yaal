package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.createArray;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] b = in.readIntArray(n);
        for (int i = 0; i < n; i++) {
            int nPos = (i + 1) % n;
            int next = b[nPos];
            if (b[i] < next) {
                long[] a = new long[n];
                a[nPos] = next;
                a[i] = b[i];
                int pos = i;
                int pos1 = nPos;
                while (pos != nPos) {
                    int pPos = pos - 1;
                    if (pPos == -1) {
                        pPos = n - 1;
                    }
                    a[pPos] = b[pPos];
                    if (a[pPos] >= a[pos]) {
                        a[pos] += ((a[pPos] - a[pos]) / a[pos1] + 1) * a[pos1];
                    }
                    pos1 = pos;
                    pos = pPos;
                }
                out.printLine("YES");
                out.printLine(a);
                return;
            }
        }
        if (b[0] == 0) {
            out.printLine("YES");
            out.printLine(createArray(n, 1));
            return;
        }
        out.printLine("NO");
    }
}
