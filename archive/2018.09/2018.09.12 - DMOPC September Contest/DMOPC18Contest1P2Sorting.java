package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.sort;

public class DMOPC18Contest1P2Sorting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] p = in.readIntArray(n);
        for (int i = 0; i < k; i++) {
            int[] arr = new int[1 + (n - 1 - i) / k];
            int at = 0;
            for (int j = i; j < n; j += k) {
                arr[at++] = p[j];
            }
            sort(arr);
            at = 0;
            for (int j = i; j < n; j += k) {
                p[j] = arr[at++];
            }
        }
        for (int i = 1; i < n; i++) {
            if (p[i] < p[i - 1]) {
                out.printLine("NO");
                return;
            }
        }
        out.printLine("YES");
    }
}
