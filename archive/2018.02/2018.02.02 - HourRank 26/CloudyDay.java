package net.egork;

import net.egork.collections.FenwickTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.binarySearch;
import static net.egork.misc.ArrayUtils.orderBy;

public class CloudyDay {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] p = in.readIntArray(n);
        int[] x = in.readIntArray(n);
        int m = in.readInt();
        int[] y = in.readIntArray(m);
        int[] r = in.readIntArray(m);
        orderBy(x, p);
        int[] left = new int[m];
        int[] right = new int[m];
        long[] delta = new long[n + 1];
        for (int i = 0; i < m; i++) {
            int from = y[i] - r[i];
            int start = binarySearch(x, from);
            if (start < 0) {
                start = -start - 1;
            }
            left[i] = start;
            int to = y[i] + r[i];
            int end = binarySearch(x, to);
            if (end < 0) {
                end = -end - 2;
            }
            right[i] = end;
            delta[start]++;
            delta[end + 1]--;
        }
        FenwickTree tree = new FenwickTree(n);
        long sunny = 0;
        long clouds = 0;
        for (int i = 0; i < n; i++) {
            clouds += delta[i];
            if (clouds == 0) {
                sunny += p[i];
            } else if (clouds == 1) {
                tree.add(i, p[i]);
            }
        }
        long maxAdd = 0;
        for (int i = 0; i < m; i++) {
            if (right[i] >= left[i]) {
                maxAdd = Math.max(maxAdd, tree.get(left[i], right[i]));
            }
        }
        out.printLine(sunny + maxAdd);
    }
}
