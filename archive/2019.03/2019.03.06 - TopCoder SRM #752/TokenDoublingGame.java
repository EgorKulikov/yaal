package net.egork;

import static java.util.Arrays.fill;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.reverse;

public class TokenDoublingGame {
    long[] aVal;
    long[] bVal;
    long[] aDel;
    long[] bDel;
    long aRes;
    long bRes;

    public int expectation(int N) {
        if (N == 1) {
            return 1;
        }
        aVal = new long[8 * N + 8];
        bVal = new long[8 * N + 8];
        aDel = new long[8 * N + 8];
        bDel = new long[8 * N + 8];
        fill(aDel, 1);
        fill(aVal, 1);
        long rev4 = reverse(4, MOD7);
        long rev2 = reverse(2, MOD7);
        for (int i = 2; i < 2 * N; i++) {
            long a = rev4;
            long b = 1 + rev2;
            int j = 2;
            int k = 2;
            long c = rev4;
            int add = 1;
            while (i > j + 1) {
                query(0, 0, 2 * N, i - j);
                b += c % MOD7;
                c *= rev2;
                c %= MOD7;
                a += aRes * c % MOD7;
                b += bRes * c % MOD7;
                k *= 2;
                j += k;
                add++;
            }
            a %= MOD7;
            b %= MOD7;
            a = 1 - a;
            if (a < 0) {
                a += MOD7;
            }
            a = reverse(a, MOD7);
            b = b * a % MOD7;
            a = rev2 * a % MOD7;
            apply(0, 0, 2 * N, 2, i, a, b);
        }
        query(0, 0, 2 * N, N);
        return (int) bRes;
    }

    private void apply(int root, int left, int right, int from, int to, long c, long d) {
        if (left > to || right < from) {
            return;
        }
        if (left >= from && right <= to) {
            apply(root, c, d);
            return;
        }
        pushDown(root);
        int middle = (left + right) >> 1;
        apply(2 * root + 1, left, middle, from, to, c, d);
        apply(2 * root + 2, middle + 1, right, from, to, c, d);
    }

    private void query(int root, int left, int right, int at) {
        if (left > at || right < at) {
            return;
        }
        if (left == right) {
            aRes = aVal[root];
            bRes = bVal[root];
            return;
        }
        int middle = (left + right) >> 1;
        pushDown(root);
        query(2 * root + 1, left, middle, at);
        query(2 * root + 2, middle + 1, right, at);
    }

    private void pushDown(int root) {
        apply(2 * root + 1, aDel[root], bDel[root]);
        apply(2 * root + 2, aDel[root], bDel[root]);
        aDel[root] = 1;
        bDel[root] = 0;
    }

    private void apply(int root, long c, long d) {
        bDel[root] = (bDel[root] + aDel[root] * d) % MOD7;
        aDel[root] = aDel[root] * c % MOD7;
        bVal[root] = (bVal[root] + aVal[root] * d) % MOD7;
        aVal[root] = aVal[root] * c % MOD7;
    }
}
