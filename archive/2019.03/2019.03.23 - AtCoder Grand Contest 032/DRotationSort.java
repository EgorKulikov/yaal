package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class DRotationSort {
    int n;
    long[][] answer;
    int[] p;
    long a;
    long b;
    int[][] qty;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        a = in.readInt();
        b = in.readInt();
        p = in.readIntArray(n);
        decreaseByOne(p);
        answer = new long[n + 1][n + 1];
        fill(answer, -1);
        qty = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                qty[i][j + 1] = qty[i][j];
                if (p[j] < i) {
                    qty[i][j + 1]++;
                }
            }
        }
        out.printLine(go(0, 0));
    }

    private long go(int x, int y) {
        if (x == n) {
            return 0;
        }
        if (answer[x][y] != -1) {
            return answer[x][y];
        }
        if (p[x] < y) {
            return answer[x][y] = go(x + 1, y);
        }
        return answer[x][y] = min(go(x + 1, y) + a, go(x + 1, max(y, p[x])) + b * query(x + 1, n - 1, y, p[x] - 1));
    }

    private int query(int posFrom, int posTo, int valFrom, int valTo) {
        if (valTo < valFrom) {
            return 0;
        }
        return qty[valTo + 1][posTo + 1] - qty[valTo + 1][posFrom] - qty[valFrom][posTo + 1] + qty[valFrom][posFrom];
    }
}
