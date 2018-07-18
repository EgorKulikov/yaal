package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static java.lang.Long.MAX_VALUE;
import static net.egork.misc.ArrayUtils.orderBy;

public class ManhattanCenter {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        in.readIntArrays(x, y);
        orderBy(x, y);
        long[] ltr = new long[n];
        int leftLimit = (k + 1) / 2;
        long infty = MAX_VALUE / 2;
        NavigableSet<Integer> points = new TreeSet<>((a, b) -> y[a] - x[a] == y[b] - x[b] ? a - b : Integer.compare
                (y[a] - x[a], y[b] - x[b]));
        long current = 0;
        for (int i = 0; i < n; i++) {
            points.add(i);
            current += y[i] - x[i];
            if (points.size() > leftLimit) {
                int id = points.pollLast();
                current -= y[id] - x[id];
            }
            if (points.size() == leftLimit) {
                ltr[i] = (long) leftLimit * x[i] + current;
            } else {
                ltr[i] = infty;
            }
        }
        long[] rtl = new long[n];
        int rightLimit = k - leftLimit;
        points = new TreeSet<>((a, b) -> y[a] + x[a] == y[b] + x[b] ? a - b : Integer.compare
                (y[a] + x[a], y[b] + x[b]));
        current = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (points.size() == rightLimit) {
                rtl[i] = current - (long) rightLimit * x[i];
            } else {
                rtl[i] = infty;
            }
            points.add(i);
            current += y[i] + x[i];
            if (points.size() > rightLimit) {
                int id = points.pollLast();
                current -= y[id] + x[id];
            }
        }
        long answer = infty;
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, ltr[i] + rtl[i]);
        }
        out.printLine(answer);
    }
}
