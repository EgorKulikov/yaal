package net.egork;

import net.egork.collections.FenwickTree;
import net.egork.collections.intcollection.Heap;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.createArray;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = in.readIntArray(n);
        int m = in.readInt();
        int[] l = new int[m];
        int[] r = new int[m];
        long[] k = new long[m];
        for (int i = 0; i < m; i++) {
            l[i] = in.readInt() - 1;
            r[i] = in.readInt() - 1;
            k[i] = in.readLong();
        }
        Heap heap = new Heap((a, b) -> x[b] != x[a] ? x[b] - x[a] : a - b, n);
        for (int i = 0; i < n; i++) {
            if (x[i] != 0) {
                heap.add(i);
            }
        }
        int[] first = createArray(n * 31, -1);
        int[] next = new int[m];
        long[] answer = new long[m];
        FenwickTree tree = new FenwickTree(n);
        for (int i = 0; i < n; i++) {
            tree.add(i, x[i]);
        }
        for (int i = 0; i < m; i++) {
            if (k[i] < first.length) {
                next[i] = first[(int) k[i]];
                first[(int) k[i]] = i;
            }
        }
        int at = 0;
        while (!heap.isEmpty()) {
            for (int i = first[at]; i != -1; i = next[i]) {
                answer[i] = tree.get(l[i], r[i]);
            }
            int current = heap.poll();
            tree.add(current, (x[current] >> 1) - x[current]);
            x[current] >>= 1;
            if (x[current] != 0) {
                heap.add(current);
            }
            at++;
        }
        for (long i : answer) {
            out.printLine(i);
        }
    }
}
