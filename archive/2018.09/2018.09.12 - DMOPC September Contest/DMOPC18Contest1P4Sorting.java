package net.egork;

import net.egork.collections.set.TreapSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.misc.ArrayUtils.order;

public class DMOPC18Contest1P4Sorting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long m = in.readLong();
        long k = in.readLong();
        int x = in.readInt();
        long[] f = in.readLongArray(n);
        int[] order = order(f);
        long min = 0;
        for (int i = 0; i < x - 1; i++) {
            min += f[order[i]];
        }
        if (min >= k) {
            out.printLine(-1);
            return;
        }
        long max = 0;
        for (int i = 0; i < x; i++) {
            max += f[order[n - i - 1]];
        }
        if (max < k) {
            out.printLine(-1);
            return;
        }
        Comparator<Integer> comparator = (a, b) -> f[a] != f[b] ? Long.compare(f[a], f[b]) : a - b;
        TreapSet<Integer> remaining = new TreapSet<>(comparator);
        NavigableSet<Integer> lower = new TreeSet<>(comparator);
        NavigableSet<Integer> upper = new TreeSet<>(comparator);
        for (int i = 0; i < n; i++) {
            remaining.add(i);
        }
        for (int i = 0; i < x - 1; i++) {
            lower.add(order[i]);
        }
        for (int i = 0; i < x; i++) {
            upper.add(order[n - i - 1]);
        }
        int[] answer = new int[n];
        for (int i = 0; i < x - 1; i++) {
            int left = 0;
            int right = remaining.size();
            while (left <= right) {
                int middle = (left + right) >> 1;
                int id = remaining.get(middle);
                long nMin = lower.contains(id) ? min : min - f[lower.last()] + f[id];
                long nMax = upper.contains(id) ? max : max - f[upper.first()] + f[id];
                if (nMin < k && nMax >= k) {
                    min = nMin;
                    max = nMax;
                    if (lower.contains(id)) {
                        lower.remove(id);
                    } else {
                        lower.pollLast();
                    }
                    if (upper.contains(id)) {
                        upper.remove(id);
                    } else {
                        upper.pollFirst();
                    }
                    remaining.remove(id);
                    answer[id] = i + 1;
                    break;
                }
                if (nMin >= k) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }
        answer[upper.first()] = x;
        int at = x + 1;
        for (int i = 0; i < n; i++) {
            if (answer[i] == 0) {
                answer[i] = at++;
            }
        }
        out.printLine(answer);
    }
}
