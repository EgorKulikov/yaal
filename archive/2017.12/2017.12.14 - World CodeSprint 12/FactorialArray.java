package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.fill;
import static net.egork.io.IOUtils.readIntArray;
import static net.egork.numbers.IntegerUtils.generateFactorial;

public class FactorialArray {
    public static final int MOD = 1000000000;
    int[][] value;
    int[] delta;
    long[] f = generateFactorial(40, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = readIntArray(in, n);
        value = new int[4 * n][40];
        delta = new int[4 * n];
        init(0, 0, n - 1, a);
        for (int i = 0; i < m; i++) {
            int type = in.readInt();
            int l = in.readInt() - 1;
            int r = in.readInt();
            if (type == 1) {
                r--;
                update(0, 0, n - 1, l, r);
            } else if (type == 2) {
                r--;
                out.printLine(query(0, 0, n - 1, l, r));
            } else {
                change(0, 0, n - 1, l, r);
            }
        }
    }

    private void change(int root, int left, int right, int at, int nVal) {
        if (left == at && right == at) {
            fill(value[root], 0);
            if (nVal < 40) {
                value[root][nVal] = 1;
            }
            return;
        }
        if (left > at || right < at) {
            return;
        }
        push(root);
        int middle = (left + right) >> 1;
        change(2 * root + 1, left, middle, at, nVal);
        change(2 * root + 2, middle + 1, right, at, nVal);
        join(root);
    }

    private long query(int root, int left, int right, int from, int to) {
        if (left > to || right < from) {
            return 0;
        }
        if (left >= from && right <= to) {
            long result = 0;
            for (int i = 0; i < 40; i++) {
                result += f[i] * value[root][i];
            }
            return result % MOD;
        }
        push(root);
        int middle = (left + right) >> 1;
        return (query(2 * root + 1, left, middle, from, to) + query(2 * root + 2, middle + 1, right, from, to)) %
                MOD;
    }

    private void update(int root, int left, int right, int from, int to) {
        if (left >= from && right <= to) {
            advance(root, 1);
            return;
        }
        if (left > to || right < from) {
            return;
        }
        push(root);
        int middle = (left + right) >> 1;
        update(2 * root + 1, left, middle, from, to);
        update(2 * root + 2, middle + 1, right, from, to);
        join(root);
    }

    private void push(int root) {
        advance(2 * root + 1, delta[root]);
        advance(2 * root + 2, delta[root]);
        delta[root] = 0;
    }

    private void advance(int root, int by) {
        if (by == 0) {
            return;
        }
        by = Math.min(by, 40);
        for (int i = 39; i >= by; i--) {
            value[root][i] = value[root][i - by];
        }
        fill(value[root], 0, by, 0);
        delta[root] += by;
    }

    private void init(int root, int left, int right, int[] a) {
        if (left == right) {
            if (a[left] < 40) {
                value[root][a[left]]++;
            }
        } else {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle, a);
            init(2 * root + 2, middle + 1, right, a);
            join(root);
        }
    }

    private void join(int root) {
        for (int i = 0; i < 40; i++) {
            value[root][i] = value[2 * root + 1][i] + value[2 * root + 2][i];
        }
    }
}
