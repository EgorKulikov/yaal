package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.minPosition;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.power;

public class DHistogramColoring {
    int n;
    int[] h;
    long[] ways;
    int[][] minAt;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        h = in.readIntArray(n);
        if (n == 1) {
            out.printLine(power(2, h[0], MOD7));
            return;
        }
        long powMult = 1;
        for (int i = 0; i < n; i++) {
            int max;
            if (i == 0) {
                max = h[1];
            } else if (i == n - 1) {
                max = h[n - 2];
            } else {
                max = max(h[i - 1], h[i + 1]);
            }
            if (h[i] > max) {
                powMult += h[i] - max;
                h[i] = max;
            }
        }
        ways = createArray(n + 1, -1L);
        minAt = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                minAt[i][j] = minPosition(h, i, j + 1);
            }
        }
        long answer = go(0);
        answer *= power(2, powMult, MOD7);
        answer %= MOD7;
        out.printLine(answer);
    }

    private long go(int at) {
        if (ways[at] != -1) {
            return ways[at];
        }
        if (at == n) {
            return ways[at] = 1;
        }
        ways[at] = 0;
        int left = at == 0 ? 1 : h[at - 1];
        for (int i = at + 1; i <= n; i++) {
            int right = i == n ? 1 : h[i];
            ways[at] += power(2, calculate(at, i - 1, left, right), MOD7) * go(i) % MOD7;
        }
        ways[at] %= MOD7;
        return ways[at];
    }

    private long calculate(int from, int to, int leftFixed, int rightFixed) {
        if (from > to) {
            return 0;
        }
        int at = minAt[from][to];
        return max(0, h[at] - max(leftFixed, rightFixed)) + calculate(from, at - 1, leftFixed, h[at]) +
                calculate(at + 1, to, h[at], rightFixed);
    }
}
