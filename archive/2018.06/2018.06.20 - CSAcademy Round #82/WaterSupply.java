package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.misc.ArrayUtils;

import static net.egork.misc.ArrayUtils.orderBy;

public class WaterSupply {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        long[] f = in.readLongArray(n);
        int[][] c = in.readIntTable(n, n);
        long left = 0;
        long right = (int) 2e9 + 1;
        int[] from = new int[n * (n - 1) / 2];
        int[] to = new int[n * (n - 1) / 2];
        int[] edges = new int[n * (n - 1) / 2];
        int at = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                from[at] = i;
                to[at] = j;
                edges[at++] = c[i][j] * 2;
            }
        }
        orderBy(edges, from, to);
        int[] order = ArrayUtils.order(f);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n + 1);
        while (true) {
            ((RecursiveIndependentSetSystem) setSystem).clear();
            long middle = (left + right + 1) >> 1;
            long cost = 0;
            int special = 0;
            int cur = 0;
            for (int i = 0; i < edges.length && setSystem.getSetCount() > 1; i++) {
                while (cur < n && 2 * f[order[cur]] + middle <= edges[i]) {
                    if (setSystem.join(order[cur], n)) {
                        cost += 2 * f[order[cur]] + middle;
                        special++;
                    }
                    cur++;
                }
                if (setSystem.join(from[i], to[i])) {
                    cost += edges[i];
                }
            }
            while (cur < n && setSystem.getSetCount() > 1) {
                if (setSystem.join(order[cur], n)) {
                    cost += 2 * f[order[cur]] + middle;
                    special++;
                }
                cur++;
            }
            if (left == right || k == special) {
                long l = cost - k * middle;
                if (l % 2 != 0) {
                    throw new RuntimeException();
                }
                out.printLine(l / 2);
                return;
            } else if (special < k) {
                right = middle - 1;
            } else {
                left = middle;
            }
        }
    }
}
