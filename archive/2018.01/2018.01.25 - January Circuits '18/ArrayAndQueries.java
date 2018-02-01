package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;

public class ArrayAndQueries {
    int[] value;
    int[] delta;
    int permutation;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] a = in.readIntArray(1 << n);
        value = new int[1 << (n + 1)];
        delta = new int[1 << (n + 1)];
        init(0, 0, (1 << n) - 1, a);
        for (int i = 0; i < q; i++) {
            int type = in.readInt();
            if (type == 1) {
                int l = in.readInt();
                int r = in.readInt();
                out.printLine(query(0, 0, (1 << n) - 1, l, r, n - 1));
            } else if (type == 2) {
                int l = in.readInt();
                int r = in.readInt();
                int v = in.readInt();
                update(0, 0, (1 << n) - 1, l, r, v, n - 1);
            } else {
                int k = in.readInt();
                permutation ^= k;
            }
        }
    }

    private void update(int root, int left, int right, int from, int to, int v, int level) {
        if (from > right || left > to) {
            return;
        }
        if (from <= left && right <= to) {
            update(root, v);
            return;
        }
        pushDown(root);
        int middle = (left + right) >> 1;
        if ((permutation >> level & 1) == 0) {
            update(2 * root + 1, left, middle, from, to, v, level - 1);
            update(2 * root + 2, middle + 1, right, from, to, v, level - 1);
        } else {
            update(2 * root + 1, left, middle, from - (1 << (level)), to - (1 << level), v, level - 1);
            update(2 * root + 2, middle + 1, right, from + (1 << level), to + (1 << level), v, level - 1);
        }
        updateValue(root);
    }

    private int query(int root, int left, int right, int from, int to, int level) {
        if (from > right || left > to) {
            return -1;
        }
        if (from <= left && right <= to) {
            return value[root];
        }
        pushDown(root);
        int middle = (left + right) >> 1;
        if ((permutation >> level & 1) == 0) {
            return max(query(2 * root + 1, left, middle, from, to, level - 1), query(2 * root + 2, middle + 1,
                    right, from, to, level - 1));
        } else {
            return max(query(2 * root + 1, left, middle, from - (1 << (level)), to - (1 << level), level - 1),
                    query(2 * root + 2, middle + 1, right, from + (1 << level), to + (1 << level), level - 1));
        }
    }

    private void pushDown(int root) {
        update(2 * root + 1, delta[root]);
        update(2 * root + 2, delta[root]);
        delta[root] = -1;
    }

    private void update(int root, int v) {
        if (v != -1) {
            delta[root] = v;
            value[root] = v;
        }
    }

    private void init(int root, int left, int right, int[] a) {
        delta[root] = -1;
        if (left == right) {
            value[root] = a[left];
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle, a);
            init(2 * root + 2, middle + 1, right, a);
            updateValue(root);
        }
    }

    private void updateValue(int root) {
        value[root] = max(value[2 * root + 1], value[2 * root + 2]);
    }
}
