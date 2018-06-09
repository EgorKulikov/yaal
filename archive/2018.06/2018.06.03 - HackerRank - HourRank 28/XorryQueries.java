package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class XorryQueries {
    int[][] num;
    int[] delta;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int p = in.readInt();
        int[] a = in.readIntArray(n);
        num = new int[4 * n][18];
        delta = new int[4 * n];
        for (int i = 0; i < n; i++) {
            update(0, 0, n - 1, max(i - p + 1, 0), min(i, n - p), a[i]);
        }
        for (int i = 0; i < m; i++) {
            int type = in.readInt();
            if (type == 1) {
                int ii = in.readInt() - 1;
                int x = in.readInt();
                update(0, 0, n - 1, max(ii - p + 1, 0), min(ii, n - p), x);
            } else {
                int l = in.readInt() - 1;
                int r = in.readInt() - 1;
                out.printLine(query(0, 0, n - 1, l, r));
            }
        }
    }

    private long query(int root, int left, int right, int from, int to) {
        if (from > right || to < left) {
            return 0;
        }
        if (from <= left && right <= to) {
            long result = 0;
            for (int i = 0; i < 18; i++) {
                result += (long)num[root][i] << i;
            }
            return result;
        }
        int middle = (left + right) >> 1;
        pushDown(root, left, right, middle);
        return query(2 * root + 1, left, middle, from, to) + query(2 * root + 2, middle + 1, right, from, to);
    }

    private void update(int root, int left, int right, int from, int to, int x) {
        if (from > right || to < left) {
            return;
        }
        if (from <= left && right <= to) {
            update(root, left, right, x);
            return;
        }
        int middle = (left + right) >> 1;
        pushDown(root, left, right, middle);
        update(2 * root + 1, left, middle, from, to, x);
        update(2 * root + 2, middle + 1, right, from, to, x);
        recalculate(root);
    }

    private void recalculate(int root) {
        for (int i = 0; i < 18; i++) {
            num[root][i] = num[2 * root + 1][i] + num[2 * root + 2][i];
        }
    }

    private void pushDown(int root, int left, int right, int middle) {
        update(2 * root + 1, left, middle, delta[root]);
        update(2 * root + 2, middle + 1, right, delta[root]);
        delta[root] = 0;
    }

    private void update(int root, int left, int right, int x) {
        for (int i = 0; i < 18; i++) {
            if ((x >> i & 1) == 1) {
                num[root][i] = right - left + 1 - num[root][i];
            }
        }
        delta[root] ^= x;
    }
}
