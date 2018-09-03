package net.egork;

import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.*;

public class TaskB {
    static class MaxTree {
        private final int[] value;

        public MaxTree(int size) {
            value = new int[size];
        }

        private int get(int to) {
            to = Math.min(to, value.length - 1);
            int result = 0;
            while (to >= 0) {
                result = max(value[to], result);
                to = (to & (to + 1)) - 1;
            }
            return result;
        }

        public void add(int at, int value) {
            while (at < this.value.length) {
                this.value[at] = max(this.value[at], value);
                at = at | (at + 1);
            }
        }
    }


    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        in.readIntArrays(a, b);
        int[] all = concatenate(a, b);
        sort(all);
        all = unique(all);
        for (int i = 0; i < n; i++) {
            a[i] = binarySearch(all, a[i]);
            b[i] = binarySearch(all, b[i]);
        }
        int[] ta = a;
        a = concatenate(a, b);
        b = concatenate(b, ta);
        boolean[] reverse = new boolean[2 * n];
        fill(reverse, n, 2 * n, true);
        int[] order = order(a);
        MaxTree direct = new MaxTree(all.length);
        MaxTree rev = new MaxTree(all.length);
        int last = -1;
        List<IntIntPair> directUpdates = new ArrayList<>();
        List<IntIntPair> reverseUpdates = new ArrayList<>();
        for (int i : order) {
            if (a[i] != last) {
                for (IntIntPair pair : directUpdates) {
                    direct.add(pair.first, pair.second);
                }
                directUpdates.clear();
                for (IntIntPair pair : reverseUpdates) {
                    rev.add(pair.first, pair.second);
                }
                reverseUpdates.clear();
            }
            if (reverse[i]) {
                directUpdates.add(new IntIntPair(b[i], rev.get(b[i] - 1) + 1));
            } else {
                reverseUpdates.add(new IntIntPair(b[i], direct.get(b[i] - 1) + 1));
            }
            last = a[i];
        }
        for (IntIntPair pair : directUpdates) {
            direct.add(pair.first, pair.second);
        }
        out.printLine(direct.get(all.length - 1));
    }
}
