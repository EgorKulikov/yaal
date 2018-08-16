package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.fill;

/**
 * @author egor@egork.net
 */
public class TaskA {

    public static final long INFTY = MAX_VALUE / 2;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        long[][] lastMax = new long[n + 1][(n + 1) / 2 + 1];
        long[][] preLastMax = new long[n + 1][(n + 1) / 2 + 1];
        long[][] noMax = new long[n + 1][(n + 1) / 2 + 1];
        fill(lastMax, INFTY);
        fill(preLastMax, INFTY);
        fill(noMax, INFTY);
        lastMax[1][1] = 0;
        noMax[1][0] = 0;
        int lastValue = -1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= (i + 1) / 2; j++) {
                if (lastMax[i][j] < INFTY) {
                    preLastMax[i + 1][j] = min(preLastMax[i + 1][j], lastMax[i][j] +
                        max(0, 1 + a[i] - a[i - 1]));
                }
                if (preLastMax[i][j] < INFTY) {
                    noMax[i + 1][j] = Math.min(noMax[i + 1][j], preLastMax[i][j]);
                    lastMax[i + 1][j + 1] = Math.min(lastMax[i + 1][j + 1], preLastMax[i][j] + max(0, 1 + lastValue -
                            a[i]));
                }
                if (noMax[i][j] < INFTY) {
                    noMax[i + 1][j] = Math.min(noMax[i + 1][j], noMax[i][j]);
                    lastMax[i + 1][j + 1] = Math.min(lastMax[i + 1][j + 1], noMax[i][j] + max(0, 1 + a[i - 1] - a[i]));
                }
            }
            lastValue = min(a[i], a[i - 1] - 1);
        }
        long[] answer = new long[(n + 1) / 2];
        for (int i = 1; i <= answer.length; i++) {
            answer[i - 1] = min(lastMax[n][i], min(preLastMax[n][i], noMax[n][i]));
        }
        out.printLine(answer);
    }
}
