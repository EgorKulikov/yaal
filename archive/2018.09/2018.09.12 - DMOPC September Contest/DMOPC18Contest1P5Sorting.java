package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static java.lang.Integer.highestOneBit;

public class DMOPC18Contest1P5Sorting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] p = in.readIntArray(n);
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[i] = highestOneBit(i ^ p[i]);
        }
        NavigableSet<Integer> set = new TreeSet<>((a, b) -> val[a] != val[b] ? val[a] - val[b] : a - b);
        for (int i = 0; i < n; i++) {
            set.add(i);
        }
        for (int i = 0; i < q; i++) {
            int u = in.readInt() - 1;
            int v = in.readInt() - 1;
            if (u != v) {
                set.remove(u);
                set.remove(v);
                int t = p[u];
                p[u] = p[v];
                p[v] = t;
                val[u] = highestOneBit(u ^ p[u]);
                val[v] = highestOneBit(v ^ p[v]);
                set.add(u);
                set.add(v);
            }
            out.printLine(val[set.last()]);
        }
    }
}
