package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskF {
    public static final int INF = Integer.MAX_VALUE / 2;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n * n);
        int[] answer = new int[n];
        fill(answer, INF);
        int[] d = new int[(n + 1) * (n + 1)];
        int[] dst = new int[n + 1];
        boolean[] was = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            fill(d, INF);
            for (int k = 0; k < n; k++) {
                if (k == i) {
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (j == i || a[k * n + j] <= 0) {
                        continue;
                    }
                    d[k * (n + 1) + j] = a[k * n + j];
                }
            }
            for (int j = 1; j < n; j *= 2) {
                for (int k = 0; k < n; k++) {
                    d[k * (n + 1) + i] = d[k * (n + 1) + n] = d[i * (n + 1) + k] = d[n * (n + 1) + k] = INF;
                    if (a[k * n + i] <= 0) {
                        continue;
                    }
                    if ((k & j) == 0) {
                        d[k * (n + 1) + i] = d[i * (n + 1) + k] = a[k * n + i];
                    } else {
                        d[k * (n + 1) + n] = d[n * (n + 1) + k] = a[k * n + i];
                    }
                }
                fill(dst, INF);
                dst[n] = 0;
                fill(was, false);
                for (int k = 0; k <= n; k++) {
                    int min = INF;
                    int at = -1;
                    for (int l = 0; l <= n; l++) {
                        if (!was[l] && dst[l] < min) {
                            min = dst[l];
                            at = l;
                        }
                    }
                    if (at == -1) {
                        break;
                    }
                    was[at] = true;
                    for (int l = 0; l <= n; l++) {
                        dst[l] = Math.min(dst[l], min + d[at * (n + 1) + l]);
                    }
                }
                answer[i] = Math.min(answer[i], dst[i]);
            }
            if (answer[i] == INF) {
                answer[i] = -1;
            }
        }
        for (int i : answer) {
            out.printLine(i);
        }
    }
}
