package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Set;

import static net.egork.misc.MiscUtils.decreaseByOne;

public class DBFS {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = new int[n - 1];
        int[] y = new int[n - 1];
        in.readIntArrays(x, y);
        int[] a = in.readIntArray(n);
        decreaseByOne(x, y, a);
        if (a[0] != 0) {
            out.printLine("No");
            return;
        }
        Set<IntIntPair> edges = new EHashSet<>();
        int[] degree = new int[n];
        for (int i = 0; i < n - 1; i++) {
            edges.add(new IntIntPair(x[i], y[i]));
            edges.add(new IntIntPair(y[i], x[i]));
            degree[x[i]]++;
            degree[y[i]]++;
        }
        for (int i = 1; i < n; i++) {
            degree[i]--;
        }
        int current = 1;
        for (int i = 0; i < n; i++) {
            int end = current + degree[a[i]];
            for (int j = current; j < end; j++) {
                if (!edges.contains(new IntIntPair(a[i], a[j]))) {
                    out.printLine("No");
                    return;
                }
            }
            current = end;
        }
        out.printLine("Yes");
    }
}
