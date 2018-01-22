package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.util.Arrays.fill;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskB {

    public static final int DAYS = 1000000;
    public static final long INFTY = 1000_000_000_000L;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        int[] d = new int[m];
        int[] f = new int[m];
        int[] t = new int[m];
        int[] c = new int[m];
        readIntArrays(in, d, f, t, c);
        decreaseByOne(d, f, t);
        int[] first = createArray(DAYS, -1);
        int[] next = new int[m];
        for (int i = 0; i < m; i++) {
            if (t[i] == -1) {
                next[i] = first[d[i]];
                first[d[i]] = i;
            }
        }
        long[] inbound = new long[DAYS];
        long[] current = createArray(n, INFTY);
        long result = INFTY * n;
        for (int i = 0; i < DAYS; i++) {
            for (int j = first[i]; j != -1; j = next[j]) {
                if (c[j] < current[f[j]]) {
                    result -= current[f[j]];
                    current[f[j]] = c[j];
                    result += c[j];
                }
            }
            inbound[i] = result;
        }
        fill(first, -1);
        for (int i = 0; i < m; i++) {
            if (f[i] == -1) {
                next[i] = first[d[i]];
                first[d[i]] = i;
            }
        }
        fill(current, INFTY);
        result = INFTY * n;
        long[] outbound = new long[DAYS];
        for (int i = DAYS - 1; i >= 0; i--) {
            for (int j = first[i]; j != -1; j = next[j]) {
                if (c[j] < current[t[j]]) {
                    result -= current[t[j]];
                    current[t[j]] = c[j];
                    result += c[j];
                }
            }
            outbound[i] = result;
        }
        long answer = Long.MAX_VALUE;
        for (int i = k + 1; i < DAYS; i++) {
            answer = Math.min(answer, inbound[i - k - 1] + outbound[i]);
        }
        if (answer >= INFTY) {
            answer = -1;
        }
        out.printLine(answer);
    }
}
